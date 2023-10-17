package com.gl.studentmanagementsystem.repository;

import com.gl.studentmanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRolesByName(String name);
}
