package com.szymon.apka.repository;

import com.szymon.apka.entity.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {
}
