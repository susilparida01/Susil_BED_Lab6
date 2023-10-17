package com.gl.studentmanagementsystem.serviceimpl;

import com.gl.studentmanagementsystem.dto.RoleDto;
import com.gl.studentmanagementsystem.entity.Role;
import com.gl.studentmanagementsystem.mapper.RoleMapper;
import com.gl.studentmanagementsystem.repository.RoleRepository;
import com.gl.studentmanagementsystem.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    final private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = RoleMapper.mapToRole(roleDto);
        return RoleMapper.mapToRoleDto(roleRepository.save(role));
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRolesByName(name);
    }
}
