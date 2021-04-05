package com.szymon.apka.controller;

import com.szymon.apka.DTO.StudentDTO;
import com.szymon.apka.entity.Student;
import com.szymon.apka.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class StudentController {

    private final ModelMapper modelMapper;

    private final StudentService studentService;

    public StudentController(ModelMapper modelMapper, StudentService studentService) {
        this.modelMapper = modelMapper;
        this.studentService = studentService;
    }

    @PostMapping(path = "/students")
    public ResponseEntity<Object> addStudent(@RequestBody @Valid StudentDTO studentDTO) {

        Student student = convertToEntity(studentDTO);

        Student savedStudent = this.studentService.addStudent(student);

        URI location = UriComponentsBuilder
                .fromPath("/students/{id}")
                .buildAndExpand(savedStudent.getStudentId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/students/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") Long id) {

        boolean isDeleted = this.studentService.deleteStudent(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/students/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable("id") Long id, @RequestBody @Valid StudentDTO studentDTO) {

        Student student = convertToEntity(studentDTO);

        boolean ifUpdated = this.studentService.updateStudent(id, student);

        if (!ifUpdated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/student")
    public ResponseEntity<Student> findStudentByFirstAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

        Optional<Student> student = this.studentService.findStudentByFirstAndLastName(firstName, lastName);

        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(student.get());
    }



    private Student convertToEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    private StudentDTO convertToDto(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
}
