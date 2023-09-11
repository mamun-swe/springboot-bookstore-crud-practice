package com.springboot.bookstore.model.dtos;

// import com.springboot.bookstore.model.Books;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BooksDto {
    @NotBlank(message = "Category is required.")
    private Long category;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Author is required.")
    private String author;

    @NotBlank(message = "Price is required.")
    private Long price;

    // public Books toBooks() {
    //     return new Books()
    //             .setCategory(category)
    //             .setName(name)
    //             .setTitle(title)
    //             .setAuthor(author)
    //             .setPrice(price);
    // }
}
