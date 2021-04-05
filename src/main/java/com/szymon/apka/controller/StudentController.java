package com.szymon.apka.controller;

import com.szymon.apka.DTO.StudentDTO;
import com.szymon.apka.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentController {

    private final ModelMapper modelMapper;

    public StudentController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/students")
    public void addStudent(@RequestBody @Valid StudentDTO studentDTO) {

        Student student = convertToEntity(studentDTO);

        System.out.println(student);

    }

    private Student convertToEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    private StudentDTO convertToDto(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
}
