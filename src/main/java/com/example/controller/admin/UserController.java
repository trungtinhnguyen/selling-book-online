package com.example.controller.admin;

import com.example.constant.SystemConstant;
import com.example.dto.RoleDto;
import com.example.dto.UserDto;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller (value = "adminUserController")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping (value = "/quan-tri/nguoi-dung/danh-sach")
    public ModelAndView userListPage() {
        ModelAndView view = new ModelAndView("admin/user/list");
        List<UserDto> model = userService.findAll();
        view.addObject(SystemConstant.MODEL, model);
        return view;
    }

    @GetMapping (value = "/quan-tri/nguoi-dung/chinh-sua")
    public ModelAndView userEditPage (@RequestParam(name = "id", required = false) Long id) {
        ModelAndView view = new ModelAndView("admin/user/edit");
        List<RoleDto> roles = roleService.findAll();
        UserDto model;
        if (id != null) {
            model = userService.findOne(id);
        } else  {
            model = new UserDto();
        }
        view.addObject(SystemConstant.MODEL, model);
        view.addObject("roles", roles);
        return view;
    }
}
