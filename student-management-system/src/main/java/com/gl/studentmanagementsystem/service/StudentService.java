package com.gl.studentmanagementsystem.service;

import com.gl.studentmanagementsystem.dto.StudentDto;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAllStudents();

    StudentDto save(StudentDto studentDto);

    StudentDto findById(long studentId);

    StudentDto update(long studentId, StudentDto studentDto);

    void deleteStudent(long studentId);
}
