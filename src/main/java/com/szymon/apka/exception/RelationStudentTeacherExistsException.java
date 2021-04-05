package com.szymon.apka.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RelationStudentTeacherExistsException extends RuntimeException {

    public RelationStudentTeacherExistsException(String message) {
        super(message);
    }
}


