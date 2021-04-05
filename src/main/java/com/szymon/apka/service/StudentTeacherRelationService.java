package com.szymon.apka.service;

import com.szymon.apka.entity.Student;
import com.szymon.apka.entity.StudentTeacher;
import com.szymon.apka.entity.StudentTeacherRelationKey;
import com.szymon.apka.entity.Teacher;
import com.szymon.apka.repository.StudentRepository;
import com.szymon.apka.repository.StudentTeacherRelationRepository;
import com.szymon.apka.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
