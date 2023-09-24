package com.springboot.bookstore.model.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BooksDto {
    @NotBlank(message = "The name is required.")
    @NotEmpty(message = "The name is required.")
    @NotNull(message = "The name is required.")
    private String name;

    @NotNull(message = "The category id is required.")
    @Min(value = 1, message = "Category id isn't valid.")
    private Long category;
}
