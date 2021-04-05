package com.szymon.apka.repository;

import com.szymon.apka.entity.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {

    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
