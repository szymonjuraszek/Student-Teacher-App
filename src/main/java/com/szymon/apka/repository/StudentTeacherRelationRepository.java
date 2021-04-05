package com.szymon.apka.repository;

import com.szymon.apka.entity.StudentTeacher;
import com.szymon.apka.entity.StudentTeacherRelationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherRelationRepository extends JpaRepository<StudentTeacher, StudentTeacherRelationKey> {
}
