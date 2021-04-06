package com.szymon.apka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String fieldOfStudy;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudentTeacher> teachers = new ArrayList<>();

    public Student() {
    }
}
