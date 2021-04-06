package com.szymon.apka.controller;

import com.szymon.apka.DTO.TeacherDTO;
import com.szymon.apka.entity.Teacher;
import com.szymon.apka.exception.NotFoundByIdException;
import com.szymon.apka.service.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .buildAndExpand(savedTeacher.getTeacherId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/teachers/{id}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("id") Long id) {

        boolean isDeleted = this.teacherService.deleteTeacher(id);

        if (!isDeleted) {
            throw new NotFoundByIdException("Not found Teacher with id: " + id);
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/teachers/{id}")
    public ResponseEntity<Object> updateTeacher(@PathVariable("id") Long id, @RequestBody @Valid TeacherDTO teacherDTO) {

        Teacher teacher = convertToEntity(teacherDTO);

        boolean ifUpdated = this.teacherService.updateTeacher(id, teacher);

        if (!ifUpdated) {
            throw new NotFoundByIdException("Not found Teacher with id: " + id);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/teacher")
    public ResponseEntity<TeacherDTO> findStudentByFirstAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

        Optional<Teacher> teacher = this.teacherService.findTeacherByFirstAndLastName(firstName, lastName);

        if (teacher.isEmpty()) {
            throw new NotFoundByIdException("Not found Teacher by firstName: " + firstName + " and lastName: " + lastName);
        }

        return ResponseEntity.ok().body(convertToDto(teacher.get()));
    }

    @GetMapping(path = "/teachers")
    public Page<Teacher> getAllTeachers(Pageable pageable) {
        return this.teacherService.getAllTeachersByPage(pageable);
    }

    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        return modelMapper.map(teacherDTO, Teacher.class);
    }

    private TeacherDTO convertToDto(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDTO.class);
    }
}
