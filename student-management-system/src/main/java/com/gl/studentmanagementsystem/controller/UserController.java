package com.gl.studentmanagementsystem.controller;

import com.gl.studentmanagementsystem.dto.RoleDto;
import com.gl.studentmanagementsystem.dto.UserDto;
import com.gl.studentmanagementsystem.service.RoleService;
import com.gl.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final private UserService userService;

    @Autowired
    private RoleService roleService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/save")
    public UserDto saveUserDetails(@RequestBody UserDto userDto) {
        return this.userService.save(userDto);
    }

    @PostMapping("/role/save")
    public RoleDto saveRoleDetails(@RequestBody RoleDto roleDto) {
        return this.roleService.save(roleDto);
    }
}
