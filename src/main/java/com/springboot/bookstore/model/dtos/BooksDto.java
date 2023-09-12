package com.springboot.bookstore.model.dtos;

import lombok.Data;

@Data
public class BooksDto {
    private Long id;
    private Long category;
    private String name;
    private String title;
    private String author;
    private Long price;
}
