package com.example.service.impl;

import com.example.constant.MessageKey;
import com.example.constant.SystemConstant;
import com.example.converter.UserConverter;
import com.example.dto.UserDto;
import com.example.entity.UserEntity;
import com.example.exception.EmailExistsException;
import com.example.exception.TellExistsException;
import com.example.exception.UsernameExistsException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final UserConverter userConverter;
    public final MessageUtils messageUtils;
    private final BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, MessageUtils messageUtils, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.messageUtils = messageUtils;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> dtos = new ArrayList<>();
        List<UserEntity> entities = userRepository.findAll();
        entities.forEach(entity -> {
            dtos.add(userConverter.toDto(entity));
        });
        return dtos;
    }

    @Override
    public UserDto findOne(Long id) {
        UserDto dto = new UserDto();
        UserEntity entity = userRepository.findOne(id);
        if (entity != null) {
            dto = userConverter.toDto(entity);
        }
        return dto;
    }

    @Override
    @Transactional
    public UserDto save (UserDto dto) {
        UserEntity entity;
        UserDto result = new UserDto();
        Map<String, String> message = new HashMap<>();
        if (dto.getId() == null) {
            dto.setStatus(1);
            dto.setPassword(bcryptEncoder.encode(dto.getPassword()));
            if (dto.getRoles() == null) {
                List<String> roles = new ArrayList<>();
                roles.add(SystemConstant.CUSTOMER);
                dto.setRoles(roles);
            }
            entity = userConverter.toEntity(dto);
        } else {
            UserEntity old = userRepository.findOne(dto.getId());
            if (dto.getPassword() == null) {
                dto.setPassword(old.getPassword());
            } else {
                dto.setPassword(bcryptEncoder.encode(dto.getPassword()));
            }
            entity = userConverter.toEntity(old, dto);
        }
        try {
            if (!isUserExist(entity)) {
                result = userConverter.toDto(userRepository.save(entity));
                if (dto.getId() != null) {
                    message = messageUtils.loadMessage(MessageKey.UPDATED_SUCCESS);
                } else {
                    message = messageUtils.loadMessage(MessageKey.REGISTERED_SUCCESS);
                }
                result.setSuccess(true);
            }
        } catch (UsernameExistsException | EmailExistsException | TellExistsException e) {
            message = messageUtils.loadMessage(e.getMessage());
            result.setSuccess(false);
        } finally {
            result.setMessage(message.get(MessageUtils.MESSAGE));
            result.setType(message.get(MessageUtils.TYPE));
        }
        return result;
    }

    @Override
    public UserDto findByUserName(String username) {
        return userConverter.toDto(userRepository.findOneByUsernameAndStatus(username, SystemConstant.ACTIVE));
    }

    @Override
    @Transactional
    public void disable(long id) {
        UserEntity entity = userRepository.findOne(id);
        entity.setStatus(SystemConstant.NONACTIVE);
        userRepository.save(entity);
    }

    private boolean isUserExist (UserEntity newEntity)
    throws UsernameExistsException, TellExistsException, EmailExistsException
    {
        List<UserEntity> entities = userRepository.findAll();
        entities.forEach(old -> {
            if (newEntity.getUsername().equals(old.getUsername())){
                throw new UsernameExistsException(MessageKey.USERNAME_EXISTED);
            } else if (newEntity.getEmail().equals(old.getEmail())) {
                throw new EmailExistsException(MessageKey.EMAIL_EXISTED);
            } else if (newEntity.getTell().equals(old.getTell())) {
                throw new TellExistsException(MessageKey.TELL_EXISTED);
            }
        });
        return false;
    }
}
