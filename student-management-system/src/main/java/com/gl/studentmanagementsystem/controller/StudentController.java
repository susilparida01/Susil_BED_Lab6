package com.gl.studentmanagementsystem.controller;

import com.gl.studentmanagementsystem.dto.StudentDto;
import com.gl.studentmanagementsystem.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        List<StudentDto> studentDtoList = studentService.findAllStudents();
        model.addAttribute("students", studentDtoList);
        return "student/list";
    }

    @GetMapping("showFormForAdd")
    public String showFormForAdd(Model model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "student/add";
    }

    @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") long studentId, Model model) {
        model.addAttribute("student", studentService.findById(studentId));
        return "student/edit";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            return "student/add";
        }
        studentService.save(studentDto);
        return "redirect:/student/list";
    }

    @PostMapping("/update")
    public String updateStudent(@RequestParam("studentId") long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            return "student/edit";
        } else {
            if (studentService.update(studentId, studentDto) == null) {
                log.info("Student data is not available for provided id");
            }
            return "redirect:/student/list";
        }
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/student/list";
    }

    @GetMapping("403")
    public ModelAndView accessDenied(Principal user) {
        ModelAndView modelView = new ModelAndView();

        if (user != null) {
            modelView.addObject("msg", "Hi " + user.getName()
                    + " you do not have permission to access this page.");
        } else {
            modelView.addObject("msg", "You do not have permission to access this page");
        }
        modelView.setViewName("403");
        return modelView;
    }
}
