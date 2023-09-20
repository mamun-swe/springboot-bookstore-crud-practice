package com.springboot.bookstore.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {
    @NotBlank(message = "The name is required.")
    @NotEmpty(message = "The name is required.")
    @NotNull(message = "The name is required.")
    private String name;
}
