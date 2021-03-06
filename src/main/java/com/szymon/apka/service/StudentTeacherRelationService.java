package com.szymon.apka.service;

import com.szymon.apka.entity.Student;
import com.szymon.apka.entity.StudentTeacher;
import com.szymon.apka.entity.StudentTeacherRelationKey;
import com.szymon.apka.entity.Teacher;
import com.szymon.apka.exception.NotFoundByIdException;
import com.szymon.apka.repository.StudentRepository;
import com.szymon.apka.repository.StudentTeacherRelationRepository;
import com.szymon.apka.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentTeacherRelationService {

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final StudentTeacherRelationRepository relationRepository;

    public StudentTeacherRelationService(
            StudentRepository studentRepository,
            TeacherRepository teacherRepository,
            StudentTeacherRelationRepository relationRepository
    ) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.relationRepository = relationRepository;
    }

    public boolean addRelationBetweenStudentAndTeacher(Long studentId, Long teacherId) {

        Optional<Student> studentOpt = this.studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            return false;
        }

        Optional<Teacher> teacherOpt = this.teacherRepository.findById(teacherId);
        if (teacherOpt.isEmpty()) {
            return false;
        }

        this.relationRepository.save(new StudentTeacher(
                studentOpt.get(),
                teacherOpt.get(),
                new StudentTeacherRelationKey(studentId, teacherId)
        ));

        return true;
    }

    public boolean removeRelation(Long studentId, Long teacherId) {
        StudentTeacherRelationKey key = new StudentTeacherRelationKey(studentId, teacherId);

        if (this.relationRepository.existsById(key)) {
            this.relationRepository.deleteById(key);

            return true;
        }

        return false;
    }

    public List<Student> getAllStudentsByTeacherId(Long teacherId) {
        return this.teacherRepository
                .findById(teacherId).orElseThrow(() -> new NotFoundByIdException("Teacher not found - " + teacherId))
                .getStudents()
                .stream()
                .map(StudentTeacher::getStudent)
                .collect(Collectors.toList());
    }

    public Set<Teacher> getAllTeachersByStudentId(Long studentId) {
        return this.studentRepository
                .findById(studentId).orElseThrow(() -> new NotFoundByIdException("Student not found - " + studentId))
                .getTeachers()
                .stream()
                .map(StudentTeacher::getTeacher)
                .collect(Collectors.toSet());
    }
}
