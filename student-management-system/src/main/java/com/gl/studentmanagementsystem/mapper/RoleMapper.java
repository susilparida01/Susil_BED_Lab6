package com.gl.studentmanagementsystem.mapper;

import com.gl.studentmanagementsystem.dto.RoleDto;
import com.gl.studentmanagementsystem.entity.Role;

public class RoleMapper {
    public static RoleDto mapToRoleDto(Role role) {
        return RoleDto.builder().id(role.getId()).name(role.getName()).build();
    }

    public static Role mapToRole(RoleDto roleDto) {
        return Role.builder().id(roleDto.getId()).name(roleDto.getName()).build();
    }
}
