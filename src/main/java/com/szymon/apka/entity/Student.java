package com.szymon.apka.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long studentId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String fieldOfStudy;

    @OneToMany(mappedBy = "student")
    private Set<StudentTeacher> teachers = new HashSet<>();

    public Student() {
    }
}
