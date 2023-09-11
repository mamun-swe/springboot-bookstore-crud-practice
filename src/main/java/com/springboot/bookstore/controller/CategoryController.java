package com.springboot.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    /** Display the list of resources */
    @GetMapping()
    ResponseEntity<String> index() {
        return new ResponseEntity<String>("Category list", HttpStatus.OK);
    }

    /** Store new resource */
    @PostMapping()
    ResponseEntity<String> store() {
        return new ResponseEntity<String>("Category created", HttpStatus.CREATED);
    }

    /** Display specific resource */
    @GetMapping("{id}")
    ResponseEntity<String> show() {
        return new ResponseEntity<String>("Specific category information", HttpStatus.OK);
    }
}
