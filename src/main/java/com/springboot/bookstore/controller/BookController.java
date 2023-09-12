package com.springboot.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bookstore.exception.ErrorResponse;
import com.springboot.bookstore.model.Books;
import com.springboot.bookstore.service.BooksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BooksService booksService;

    /** Display the list of resources */
    @GetMapping()
    ResponseEntity<List<Books>> index() {
        List<Books> data = booksService.getAllBooks();
        return new ResponseEntity<List<Books>>(data, HttpStatus.OK);
    }

    /** Store new resource */
    @PostMapping()
    ResponseEntity<Long> store(@Valid @RequestBody Books documents) {
        booksService.createBook(documents);
        return new ResponseEntity<Long>(documents.getId(), HttpStatus.CREATED);
    }

    /** Display the specific resource */
    @GetMapping("{id}")
    ResponseEntity<?> show(@PathVariable("id") Long id) {
        try {
            Books bookData = booksService.getBookById(id);
            return new ResponseEntity<Books>(bookData, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(false, "Book not found.");
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    /** Update specific resource */
    @PutMapping("{id}")
    ResponseEntity<String> update(@RequestBody Books documents, @PathVariable(name = "id", required = true) Long id) {
        booksService.updateBook(documents, id);
        return new ResponseEntity<String>("Book updated.", HttpStatus.OK);
    }

    /** Destroy specific resource */
    @DeleteMapping("{id}")
    ResponseEntity<String> destroy(@PathVariable(name = "id", required = true) Long id) {
        booksService.destroyBook(id);
        return new ResponseEntity<String>("Book deleted.", HttpStatus.OK);
    }

}
