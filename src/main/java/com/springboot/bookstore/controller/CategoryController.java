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
import com.springboot.bookstore.model.Category;
import com.springboot.bookstore.model.dtos.CategoryDto;
import com.springboot.bookstore.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /** Display the list of resources */
    @GetMapping()
    ResponseEntity<Object> index() {
        try {
            List<Category> data = this.categoryService.getAllCategories();

            return Response.Success(HttpStatus.OK, "List of categories.", data);
        } catch (Exception e) {
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }

    /** Store new resource */
    @PostMapping()
    ResponseEntity<Object> store(@RequestBody @Valid CategoryDto categoryDto) {
        try {
            System.out.println(categoryDto.toString());
            // /** Convert DTO to entity */
            // Category category = new Category();
            // category.setName(categoryDto.getName());

            // /** Sotre data */
            // this.categoryService.createCategory(category);

            return Response.Success(HttpStatus.CREATED, "Category created.");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage().toString());
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }

    /** Display specific resource */
    @GetMapping("{id}")
    ResponseEntity<Object> show(@PathVariable(name = "id", required = true) Long id) {
        try {
            Optional<Category> category = this.categoryService.geCategoryById(id);
            if (!category.isPresent()) {
                List<String> errorsList = Arrays.asList("Category isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errorsList);
            }

            return Response.Success(HttpStatus.OK, "Category information.", category);
        } catch (Exception e) {
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }

    /** Update specific resource */
    @PutMapping("{id}")
    ResponseEntity<Object> update(
            @RequestBody CategoryDto documents,
            @PathVariable(name = "id", required = true) Long id) {
        try {
            Optional<Category> category = this.categoryService.geCategoryById(id);
            if (!category.isPresent()) {
                List<String> errorsList = Arrays.asList("Category isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errorsList);
            }

            this.categoryService.updateCategory(documents, id);
            return Response.Success(HttpStatus.OK, "Category updated.");
        } catch (Exception e) {
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }

    /** Delete specific resource */
    @DeleteMapping("{id}")
    ResponseEntity<Object> destroy(@PathVariable(name = "id", required = true) Long id) {
        try {
            Optional<Category> category = this.categoryService.geCategoryById(id);
            if (!category.isPresent()) {
                List<String> errorsList = Arrays.asList("Category isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errorsList);
            }

            this.categoryService.destroyCategory(id);
            return Response.Success(HttpStatus.OK, "Category deleted.");
        } catch (Exception e) {
            List<String> errorsList = Arrays.asList(e.getMessage());
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Something going wrong.", errorsList);
        }
    }
}
