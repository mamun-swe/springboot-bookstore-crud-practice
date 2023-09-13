package com.springboot.bookstore.controller;

import java.util.List;
import java.util.Optional;

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
import com.springboot.bookstore.exception.SuccessResponse;
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
            Optional<Books> bookData = booksService.getBookById(id);
            if (!bookData.isPresent()) {
                ErrorResponse errorResponse = new ErrorResponse(false, "Book information.");
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<Books>(bookData.get(), HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(false, "Book not found.");
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    /** Update specific resource */
    @PutMapping("{id}")
    ResponseEntity<?> update(@RequestBody Books documents, @PathVariable(name = "id", required = true) Long id) {
        try {
            /** Check book availability */
            Optional<Books> bookData = booksService.getBookById(id);
            if (!bookData.isPresent()) {
                ErrorResponse errorResponse = new ErrorResponse(false, "Book not found.");
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
            }

            booksService.updateBook(documents, id);
            SuccessResponse successResponse = new SuccessResponse(true, "Book updated.");
            return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(false, "Book not found.");
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    /** Destroy specific resource */
    @DeleteMapping("{id}")
    ResponseEntity<?> destroy(@PathVariable(name = "id", required = true) Long id) {
        /** Check book availability */
        Optional<Books> bookData = booksService.getBookById(id);
        if (!bookData.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse(false, "Book not found.");
            return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
        }

        booksService.destroyBook(id);
        SuccessResponse successResponse = new SuccessResponse(true, "Book deleted.");
        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
    }

}
