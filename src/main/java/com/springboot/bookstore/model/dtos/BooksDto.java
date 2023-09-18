package com.springboot.bookstore.model.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BooksDto {
    @NotEmpty(message = "The name is required.")
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String name;
}
