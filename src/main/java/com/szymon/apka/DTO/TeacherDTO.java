package com.szymon.apka.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.szymon.apka.entity.Student;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class TeacherDTO {

    @Size(min = 2, message = "Field: 'firstName' must have more than 2 letters.")
    @NotNull(message = "Field: 'firstName' can't be NULL.")
    private String firstName;

    @Size(min = 2, message = "Field: 'lastName' must have more than 2 letters.")
    @NotNull(message = "Field: 'lastName' can't be NULL.")
    private String lastName;

    @Min(value = 19, message = "Field: 'age' can't have value below 18.")
    @NotNull(message = "Field: 'age' can't be NULL.")
    private Integer age;

    //    bardzo ogolny regex, lepiej wysylac email aby potwierdzic adres
    @Email(regexp = ".+[@].+[.].+")
    @NotNull(message = "Field: 'email' can't be NULL.")
    private String email;

    @NotNull(message = "Field: 'subject' can't be NULL.")
    @NotBlank(message = "Field: 'subject' can't be blank.")
    private String subject;

    @JsonProperty
    private Set<Student> students = new HashSet<>();
}
