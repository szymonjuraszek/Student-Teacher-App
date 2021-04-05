package com.szymon.apka.repository;

import com.szymon.apka.entity.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
