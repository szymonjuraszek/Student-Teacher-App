package com.szymon.apka.controller;

import com.szymon.apka.DTO.StudentDTO;
import com.szymon.apka.DTO.TeacherDTO;
import com.szymon.apka.service.StudentTeacherRelationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentTeacherRelationController {

    private final StudentTeacherRelationService relationService;

    private final ModelMapper modelMapper;

    public StudentTeacherRelationController(StudentTeacherRelationService relationService, ModelMapper modelMapper) {
        this.relationService = relationService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/students/{studentId}/teachers/{teacherId}")
    public ResponseEntity<Object> addTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        return addRelation(studentId, teacherId);
    }

    @PostMapping(path = "/teachers/{teacherId}/students/{studentId}")
    public ResponseEntity<Object> addStudentToTeacher(@PathVariable Long studentId, @PathVariable Long teacherId) {
        return addRelation(studentId, teacherId);
    }

    @DeleteMapping(path = "/relation/studentTeacher")
    public ResponseEntity<Object> removeRelation(@RequestParam Long studentId, @RequestParam Long teacherId) {
        boolean ifRemoved = this.relationService.removeRelation(studentId, teacherId);

        if (!ifRemoved) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/students/{studentId}/teachers")
    public List<TeacherDTO> getAllTeachersByStudentId(@PathVariable Long studentId) {
        return this.relationService.getAllTeachersByStudentId(studentId).stream()
                .map(user -> modelMapper.map(user, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/teachers/{teacherId}/students")
    public List<StudentDTO> getAllStudentsByTeacherId(@PathVariable Long teacherId) {
        return this.relationService.getAllStudentsByTeacherId(teacherId).stream()
                .map(user -> modelMapper.map(user, StudentDTO.class))
                .collect(Collectors.toList());
    }

    private ResponseEntity<Object> addRelation(Long studentId, Long teacherId) {
        boolean ifAdded = this.relationService.addRelationBetweenStudentAndTeacher(studentId, teacherId);

        if (!ifAdded) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
