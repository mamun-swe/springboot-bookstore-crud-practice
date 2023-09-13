package com.springboot.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bookstore.model.Books;
import com.springboot.bookstore.repository.BooksRepository;

@Service
public class BooksService {

    @Autowired
    BooksRepository booksRepository;

    /** get all books record */
    public List<Books> getAllBooks() {
        List<Books> books = new ArrayList<Books>();
        booksRepository.findAll().forEach(item -> books.add(item));
        return books;
    }

    /** getting a specific record by id */
    public Optional<Books> getBookById(Long id) {
        return booksRepository.findById(id);
    }

    /** cerate a specific record */
    public void createBook(Books documents) {
        booksRepository.save(documents);
    }

    /** update a specific record */
    public void updateBook(Books documents, Long id) {
        booksRepository.save(documents);
    }

    /** delete a specific record */
    public void destroyBook(Long id) {
        booksRepository.deleteById(id);
    }
}
