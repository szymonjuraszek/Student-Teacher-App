package com.szymon.apka.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class StudentTeacherRelationKey implements Serializable {

    @Column(name = "student_id")
    Long studentId;

    @Column(name = "teacher_id")
    Long teacherId;

    public StudentTeacherRelationKey(Long studentId, Long teacherId) {
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    public StudentTeacherRelationKey() {

    }
}
