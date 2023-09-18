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
import com.springboot.bookstore.model.dtos.BooksDto;
import com.springboot.bookstore.service.BooksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BooksService booksService;

    /** Display the list of resources */
    @GetMapping()
    ResponseEntity<SuccessResponse> index() {
        List<Books> data = booksService.getAllBooks();
        SuccessResponse successResponse = new SuccessResponse(true, "List of books.", data);
        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
    }

    /** Store new resource */
    @PostMapping()
    ResponseEntity<?> store(@Valid @RequestBody BooksDto booksDto) {
        /** Convert Books DTO to entity */
        Books books = new Books();
        books.setName(booksDto.getName());
        booksService.createBook(books);

        /** convert response */
        SuccessResponse successResponse = new SuccessResponse(null, null);
        successResponse.setStatus(true);
        successResponse.setMessage("Book created.");

        return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.CREATED);
        // try {
        // /** Convert Books DTO to entity */
        // Books books = new Books();
        // books.setName(booksDto.getName());
        // booksService.createBook(books);

        // /** convert response */
        // SuccessResponse successResponse = new SuccessResponse(null, null);
        // successResponse.setStatus(true);
        // successResponse.setMessage("Book created.");

        // return new ResponseEntity<SuccessResponse>(successResponse,
        // HttpStatus.CREATED);
        // } catch (Exception e) {
        // ErrorResponse errorResponse = new ErrorResponse(false, "Something going
        // wrong.");
        // return new ResponseEntity<ErrorResponse>(errorResponse,
        // HttpStatus.INTERNAL_SERVER_ERROR);
        // }
    }

    /** Display the specific resource */
    @GetMapping("{id}")
    ResponseEntity<?> show(@PathVariable("id") Long id) {
        try {
            Optional<Books> bookData = booksService.getBookById(id);

            /** handle null data response */
            if (!bookData.isPresent()) {
                SuccessResponse successResponse = new SuccessResponse(false, "Book information.", null);

                return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.NOT_FOUND);
            }

            /** Handle present data response */
            SuccessResponse successResponse = new SuccessResponse(true, "Book information.", bookData.get());

            return new ResponseEntity<SuccessResponse>(successResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(false, "Book not found.", "Somethig going wrong.");
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
