package com.gl.studentmanagementsystem.service;

import com.gl.studentmanagementsystem.dto.RoleDto;
import com.gl.studentmanagementsystem.entity.Role;

public interface RoleService {

    RoleDto save(RoleDto role);

    Role getRoleByName(String name);
}
