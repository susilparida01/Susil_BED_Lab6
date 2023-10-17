package com.gl.studentmanagementsystem.serviceimpl;

import com.gl.studentmanagementsystem.dto.UserDto;
import com.gl.studentmanagementsystem.entity.Role;
import com.gl.studentmanagementsystem.entity.User;
import com.gl.studentmanagementsystem.mapper.UserMapper;
import com.gl.studentmanagementsystem.repository.RoleRepository;
import com.gl.studentmanagementsystem.repository.UserRepository;
import com.gl.studentmanagementsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = UserMapper.mapToUser(userDto);
        if (user.getRoles() != null) {
            Set<Role> userRoles = user.getRoles().stream().map(role -> {
                Optional<Role> roleById = roleRepository.findById(role.getId());
                return roleById.orElse(null);
            }).collect(Collectors.toSet());
            user.setRoles(userRoles);
            user.getRoles().removeIf(Objects::isNull);
            if (user.getRoles().size() != userDto.getRoles().size()) {
                log.info("Few Roles passed are not available. creating user with other role(s) which matches "
                        + user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
            }
            if (user.getRoles().isEmpty()) {
                log.info("There are no roles available matching. creating user with default role: USER");
                user.getRoles().add(this.roleRepository.getRolesByName("USER"));
            }
        } else {
            user.addRole(this.roleRepository.getRolesByName("USER"));
        }
        return UserMapper.mapToUserDto(userRepository.save(user));
    }
}
