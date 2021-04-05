package com.szymon.apka.service;

import com.szymon.apka.entity.Teacher;
import com.szymon.apka.exception.RelationStudentTeacherExistsException;
import com.szymon.apka.repository.StudentTeacherRelationRepository;
import com.szymon.apka.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    private final StudentTeacherRelationRepository relationRepository;

    public TeacherService(TeacherRepository teacherRepository, StudentTeacherRelationRepository relationRepository) {
        this.teacherRepository = teacherRepository;
        this.relationRepository = relationRepository;
    }

    public Teacher addTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    public boolean deleteTeacher(Long id) {

        if(this.relationRepository.checkIfRelationExistsForTeacherId(id) == 1) {
            throw new RelationStudentTeacherExistsException("Can't delete Teacher with id: " + id + " , because relation exists");
        }

        if (this.teacherRepository.existsById(id)) {
            this.teacherRepository.deleteById(id);

            return true;
        }

        return false;
    }

    public boolean updateTeacher(Long id, Teacher teacher) {

        Optional<Teacher> currentTeacher = this.teacherRepository.findById(id);

        if (currentTeacher.isPresent()) {
            teacher.setTeacherId(id);
            this.teacherRepository.save(teacher);

            return true;
        }

        return false;
    }

    public Optional<Teacher> findTeacherByFirstAndLastName(String firstName, String lastName) {
        return this.teacherRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Page<Teacher> getAllTeachersByPage(Pageable pageable) {
        return this.teacherRepository.findAll(pageable);
    }
}
