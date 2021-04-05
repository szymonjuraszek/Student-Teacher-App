package com.szymon.apka.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long teacherId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String subject;

    @OneToMany(mappedBy = "teacher")
    private Set<StudentTeacher> students = new HashSet<>();

    public Teacher() {
    }
}
