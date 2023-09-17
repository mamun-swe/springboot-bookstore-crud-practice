package com.springboot.bookstore.model.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BooksDto {
    @NotEmpty(message = "The name is required.")
    private String name;

    @NotEmpty(message = "The title is required.")
    private String title;

    @NotEmpty(message = "The author is required.")
    private String author;

    @NotEmpty(message = "The price is required.")
    private Long price;
}
