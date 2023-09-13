package com.springboot.bookstore.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessResponse {
    private Boolean status;
    private String message;

    public SuccessResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
