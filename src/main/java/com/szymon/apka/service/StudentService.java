package com.szymon.apka.service;

import com.szymon.apka.entity.Student;
import com.szymon.apka.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public boolean deleteStudent(Long id) {

        if (this.studentRepository.existsById(id)) {
            this.studentRepository.deleteById(id);
        }

        return false;
    }

    public boolean updateStudent(Long id, Student student) {

        Optional<Student> currentStudent = this.studentRepository.findById(id);

        if (currentStudent.isPresent()) {
            student.setStudentId(id);
            this.studentRepository.save(student);

            return true;
        }

        return false;
    }

    public Optional<Student> findStudentByFirstAndLastName(String firstName, String lastName) {
        return this.studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Page<Student> getAllStudentsByPage(Pageable pageable) {
        return this.studentRepository.findAll(pageable);
    }
}
