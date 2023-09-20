package com.springboot.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bookstore.model.Books;
import com.springboot.bookstore.model.dtos.BooksDto;
import com.springboot.bookstore.repository.BooksRepository;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    /** get all books record */
    public List<Books> getAllBooks() {
        List<Books> books = new ArrayList<Books>();
        this.booksRepository.findAll().forEach(item -> books.add(item));
        return books;
    }

    /** getting a specific record by id */
    public Optional<Books> getBookById(Long id) {
        return this.booksRepository.findById(id);
    }

    /** cerate a specific record */
    public void createBook(Books documents) {
        this.booksRepository.save(documents);
    }

    /** update a specific record */
    public void updateBook(BooksDto documents, Long id) {
        Books books = this.booksRepository.findById(id).get();
        books.setName(documents.getName());
        this.booksRepository.save(books);
    }

    /** delete a specific record */
    public void destroyBook(Long id) {
        this.booksRepository.deleteById(id);
    }
}
