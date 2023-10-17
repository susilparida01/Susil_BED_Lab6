package com.gl.studentmanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class RoleDto {
    private long id;

    @NotEmpty
    private String name;
}
