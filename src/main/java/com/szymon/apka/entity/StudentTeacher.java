package com.szymon.apka.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StudentTeacher {

    @EmbeddedId
    StudentTeacherRelationKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    public StudentTeacher() {
    }

    public StudentTeacher(Student student, Teacher teacher, StudentTeacherRelationKey id) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
    }
}
