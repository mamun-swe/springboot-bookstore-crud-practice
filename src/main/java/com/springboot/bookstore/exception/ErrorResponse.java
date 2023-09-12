package com.springboot.bookstore.exception;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private Boolean status;
    private String message;
    private List<String> errors;

    public ErrorResponse(Boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
        // this.errors = errors;
    }
}
