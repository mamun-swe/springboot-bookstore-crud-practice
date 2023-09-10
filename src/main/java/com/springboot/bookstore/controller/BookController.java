package com.springboot.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    /** Display the list of resources */
    @GetMapping()
    ResponseEntity<String> index() {
        return new ResponseEntity<String>("Book list", HttpStatus.OK);
    }

    /** Store new resource */
    @PostMapping()
    ResponseEntity<String> store() {
        return new ResponseEntity<String>("Book created.", HttpStatus.CREATED);
    }

    /** Display the specific resource */
    @GetMapping("{id}")
    ResponseEntity<String> show() {
        return new ResponseEntity<String>("Specific book information.", HttpStatus.OK);
    }

    /** Update specific resource */
    @PutMapping("{id}")
    ResponseEntity<String> update() {
        return new ResponseEntity<String>("Book updated.", HttpStatus.OK);
    }

    /** Destroy specific resource */
    @DeleteMapping("{id}")
    ResponseEntity<String> destroy() {
        return new ResponseEntity<String>("Book deleted.", HttpStatus.OK);
    }

}
