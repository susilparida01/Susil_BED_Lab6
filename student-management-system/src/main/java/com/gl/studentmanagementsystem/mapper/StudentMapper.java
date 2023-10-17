package com.gl.studentmanagementsystem.mapper;

import com.gl.studentmanagementsystem.dto.StudentDto;
import com.gl.studentmanagementsystem.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .course(student.getCourse())
                .country(student.getCountry())
                .build();
    }

    public static Student mapToStudent(StudentDto studentDto) {
        return Student.builder()
                .id(studentDto.getId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .course(studentDto.getCourse())
                .country(studentDto.getCountry())
                .build();
    }
}
