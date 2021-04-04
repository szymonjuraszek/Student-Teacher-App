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
    @Setter(AccessLevel.NONE)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String subject;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Student_Teacher",
            joinColumns = { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private Set<Student> students = new HashSet<>();

    public Teacher() {
    }
}
