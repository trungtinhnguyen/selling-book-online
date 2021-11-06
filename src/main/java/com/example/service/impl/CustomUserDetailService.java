package com.example.service.impl;

import com.example.constant.SystemConstant;
import com.example.dto.MyUser;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<GrantedAuthority> authorities = new ArrayList<>();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser userDetails;
        UserEntity userEntity = userRepository.findOneByUsernameAndStatus(username, SystemConstant.ACTIVE);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        for (RoleEntity role : userEntity.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        userDetails = new MyUser(userEntity.getUsername(), userEntity.getPassword(),
                true,true,true,true, authorities);
        userDetails.setFullName(userEntity.getFullName());

        return userDetails;
    }
}
