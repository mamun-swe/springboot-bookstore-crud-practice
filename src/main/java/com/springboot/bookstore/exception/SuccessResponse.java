package com.springboot.bookstore.exception;

import java.util.Arrays;
import java.util.List;

import com.springboot.bookstore.model.Books;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessResponse {
    private Boolean status;
    private String message;
    private List<Books> data;

    public SuccessResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public SuccessResponse(Boolean status, String message, Books data) {
        this.status = status;
        this.message = message;
        this.data = Arrays.asList(data);
    }

    public SuccessResponse(Boolean status, String message, List<Books> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
