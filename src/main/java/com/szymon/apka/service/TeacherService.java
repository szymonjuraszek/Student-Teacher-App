package com.szymon.apka.service;

import com.szymon.apka.entity.Student;
import com.szymon.apka.entity.Teacher;
import com.szymon.apka.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher addTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    public boolean deleteTeacher(Long id) {

        if (this.teacherRepository.existsById(id)) {
            this.teacherRepository.deleteById(id);
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
}
