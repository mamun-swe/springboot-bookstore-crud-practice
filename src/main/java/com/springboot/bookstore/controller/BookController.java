package com.springboot.bookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.springboot.bookstore.exception.Response;
import com.springboot.bookstore.model.Books;
import com.springboot.bookstore.model.dtos.BooksDto;
import com.springboot.bookstore.service.BooksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BooksService booksService;

    /** Display the list of resources */
    @GetMapping()
    ResponseEntity<Object> index() {
        try {
            List<Books> data = this.booksService.getAllBooks();

            return Response.Success(HttpStatus.OK, "List of books.", data);
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Store new resource */
    @PostMapping()
    ResponseEntity<Object> store(@Valid @RequestBody BooksDto booksDto) {
        try {
            /** Convert Books DTO to entity */
            Books books = new Books();
            books.setName(booksDto.getName());

            /** Sotre data */
            this.booksService.createBook(books);

            return Response.Success(HttpStatus.CREATED, "Book created.");
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Display the specific resource */
    @GetMapping("{id}")
    ResponseEntity<Object> show(@PathVariable(name = "id", required = true) Long id) {
        try {
            Optional<Books> bookData = this.booksService.getBookById(id);

            /** handle null data response */
            if (!bookData.isPresent()) {
                return Response.Success(HttpStatus.OK, "Book information.", null);
            }

            /** Handle present data response */
            return Response.Success(HttpStatus.OK, "Book information.", bookData.get());
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Update specific resource */
    @PutMapping("{id}")
    ResponseEntity<Object> update(@Valid @RequestBody BooksDto documents,
            @PathVariable(name = "id", required = true) Long id) {
        try {
            /** Check book availability */
            Optional<Books> bookData = this.booksService.getBookById(id);
            if (!bookData.isPresent()) {
                Map<String, String> errors = new HashMap<>();
                errors.put("id", "Book isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errors);
            }

            this.booksService.updateBook(documents, id);
            return Response.Success(HttpStatus.OK, "Book updated.");
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Destroy specific resource */
    @DeleteMapping("{id}")
    ResponseEntity<Object> destroy(@PathVariable(name = "id", required = true) Long id) {
        try {
            /** Check book availability */
            Optional<Books> bookData = this.booksService.getBookById(id);
            if (!bookData.isPresent()) {
                Map<String, String> errors = new HashMap<>();
                errors.put("id", "Book isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errors);
            }

            this.booksService.destroyBook(id);
            return Response.Success(HttpStatus.OK, "Book deleted.");
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }
}
