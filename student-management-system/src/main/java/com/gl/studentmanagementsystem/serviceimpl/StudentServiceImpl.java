package com.gl.studentmanagementsystem.serviceimpl;

import com.gl.studentmanagementsystem.dto.StudentDto;
import com.gl.studentmanagementsystem.mapper.StudentMapper;
import com.gl.studentmanagementsystem.repository.StudentRepository;
import com.gl.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> findAllStudents() {
        return studentRepository.findAll().stream().map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        return StudentMapper
                .mapToStudentDto(studentRepository.save(StudentMapper.mapToStudent(studentDto)));
    }

    @Override
    public StudentDto findById(long studentId) {
        return studentRepository.findById(studentId).map(StudentMapper::mapToStudentDto)
                .orElse(null);
    }

    @Override
    public StudentDto update(long studentId, StudentDto studentDto) {
        StudentDto student = findById(studentId);
        if (student != null) {
            studentDto.setId(student.getId());
            return save(studentDto);
        }
        return null;
    }

    @Override
    public void deleteStudent(long studentId) {
        StudentDto studentDto = findById(studentId);
        if (studentDto != null) {
            studentRepository.deleteById(studentId);
        }
    }
}
