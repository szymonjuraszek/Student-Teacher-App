package com.szymon.apka.controller;

import com.szymon.apka.service.StudentTeacherRelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentTeacherRelationController {

    private final StudentTeacherRelationService relationService;

    public StudentTeacherRelationController(StudentTeacherRelationService relationService) {
        this.relationService = relationService;
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

        if(!ifRemoved) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    private ResponseEntity<Object> addRelation(Long studentId, Long teacherId) {
        boolean ifAdded = this.relationService.addRelationBetweenStudentAndTeacher(studentId, teacherId);

        if(!ifAdded) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
