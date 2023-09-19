package com.springboot.bookstore.controller;

import java.util.Arrays;
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
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
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
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }

    /** Display the specific resource */
    @GetMapping("{id}")
    ResponseEntity<Object> show(@PathVariable(name = "id", required = true) Long id) {
        try {
            Optional<Books> bookData = this.booksService.getBookById(id);

            /** handle null data response */
            if (!bookData.isPresent()) {
                List<String> errorsList = Arrays.asList("Book isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errorsList);
            }

            /** Handle present data response */
            return Response.Success(HttpStatus.OK, "Book information.", bookData.get());
        } catch (Exception e) {
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }

    /** Update specific resource */
    @PutMapping("{id}")
    ResponseEntity<Object> update(@RequestBody Books documents, @PathVariable(name = "id", required = true) Long id) {
        try {
            /** Check book availability */
            Optional<Books> bookData = this.booksService.getBookById(id);
            if (!bookData.isPresent()) {
                List<String> errorsList = Arrays.asList("Book isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errorsList);
            }

            this.booksService.updateBook(documents, id);
            return Response.Success(HttpStatus.OK, "Book updated.");
        } catch (Exception e) {
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }

    /** Destroy specific resource */
    @DeleteMapping("{id}")
    ResponseEntity<Object> destroy(@PathVariable(name = "id", required = true) Long id) {
        try {
            /** Check book availability */
            Optional<Books> bookData = this.booksService.getBookById(id);
            if (!bookData.isPresent()) {
                List<String> errorsList = Arrays.asList("Book isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errorsList);
            }

            this.booksService.destroyBook(id);
            return Response.Success(HttpStatus.OK, "Book deleted.");
        } catch (Exception e) {
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }
}
