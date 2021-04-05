package com.szymon.apka.repository;

import com.szymon.apka.entity.StudentTeacher;
import com.szymon.apka.entity.StudentTeacherRelationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherRelationRepository extends JpaRepository<StudentTeacher, StudentTeacherRelationKey> {

    @Query("SELECT COUNT(st) FROM StudentTeacher as st where st.id.studentId = :studentId")
    int checkIfRelationExistsForStudentId(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(st) FROM StudentTeacher as st where st.id.teacherId = :teacherId")
    int checkIfRelationExistsForTeacherId(@Param("teacherId") Long teacherId);
}
