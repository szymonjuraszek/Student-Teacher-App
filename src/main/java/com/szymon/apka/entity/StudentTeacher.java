package com.szymon.apka.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StudentTeacher {

    @EmbeddedId
    StudentTeacherRelationKey id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne
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
