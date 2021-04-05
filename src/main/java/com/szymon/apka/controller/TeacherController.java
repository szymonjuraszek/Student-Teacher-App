package com.szymon.apka.controller;

import com.szymon.apka.DTO.TeacherDTO;
import com.szymon.apka.entity.Teacher;
import com.szymon.apka.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class TeacherController {

    private final ModelMapper modelMapper;

    private final TeacherService teacherService;

    public TeacherController(ModelMapper modelMapper, TeacherService teacherService) {
        this.modelMapper = modelMapper;
        this.teacherService = teacherService;
    }

    @PostMapping(path = "/teachers")
    public ResponseEntity<Object> addTeacher(@RequestBody @Valid TeacherDTO teacherDTO) {

        Teacher teacher = convertToEntity(teacherDTO);

        Teacher savedTeacher = this.teacherService.addTeacher(teacher);

        URI location = UriComponentsBuilder
                .fromPath("/teachers/{id}")
                .buildAndExpand(savedTeacher.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/teachers/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("id") Long id) {

        boolean isDeleted = this.teacherService.deleteTeacher(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/teachers/{id}")
    public ResponseEntity<Object> updateTeacher(@PathVariable("id") Long id, @RequestBody @Valid TeacherDTO teacherDTO) {

        Teacher teacher = convertToEntity(teacherDTO);

        boolean ifUpdated = this.teacherService.updateTeacher(id, teacher);

        if (!ifUpdated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/teacher")
    public ResponseEntity<Teacher> findStudentByFirstAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

        Optional<Teacher> teacher = this.teacherService.findTeacherByFirstAndLastName(firstName, lastName);

        if (teacher.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(teacher.get());
    }

    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        return modelMapper.map(teacherDTO, Teacher.class);
    }

    private TeacherDTO convertToDto(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDTO.class);
    }
}
