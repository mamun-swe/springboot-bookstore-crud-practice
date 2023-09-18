package com.springboot.bookstore.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private Boolean status;
    private String message;
    private List<String> errors;

    public ErrorResponse(Boolean status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(Boolean status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
