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
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Store new resource */
    @PostMapping()
    ResponseEntity<Object> store(@RequestBody @Valid CategoryDto categoryDto) {
        try {
            /** Check unique name */
            Optional<Category> availableCategory = this.categoryService.geCategoryByName(categoryDto.getName());
            if (availableCategory.isPresent()) {
                Map<String, String> errors = new HashMap<>();
                errors.put("name", "The name already taken.");

                return Response.Error(HttpStatus.CONFLICT, "Name exist.", errors);
            }

            this.categoryService.createCategory(categoryDto);

            return Response.Success(HttpStatus.CREATED, "Category created.");
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Display specific resource */
    @GetMapping("{id}")
    ResponseEntity<Object> show(@PathVariable(name = "id", required = true) Long id) {
        try {
            Optional<Category> category = this.categoryService.geCategoryById(id);
            if (!category.isPresent()) {
                return Response.Success(HttpStatus.OK, "Category information.", null);
            }

            return Response.Success(HttpStatus.OK, "Category information.", category);
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Update specific resource */
    @PutMapping("{id}")
    ResponseEntity<Object> update(
            @Valid @RequestBody CategoryDto documents,
            @PathVariable(name = "id", required = true) Long id) {
        try {
            /** Check availability */
            Optional<Category> category = this.categoryService.geCategoryById(id);
            if (!category.isPresent()) {
                Map<String, String> errors = new HashMap<>();
                errors.put("id", "Category isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errors);
            }

            /** Check unique name */
            

            this.categoryService.updateCategory(documents, id);
            return Response.Success(HttpStatus.OK, "Category updated.");
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }

    /** Delete specific resource */
    @DeleteMapping("{id}")
    ResponseEntity<Object> destroy(@PathVariable(name = "id", required = true) Long id) {
        try {
            Optional<Category> category = this.categoryService.geCategoryById(id);
            if (!category.isPresent()) {
                Map<String, String> errors = new HashMap<>();
                errors.put("id", "Category isn't available.");
                return Response.Error(HttpStatus.NOT_FOUND, "Not found.", errors);
            }

            this.categoryService.destroyCategory(id);
            return Response.Success(HttpStatus.OK, "Category deleted.");
        } catch (Exception e) {
            Map<String, String> errors = new HashMap<>();
            errors.put("server", "Something going wrong.");
            return Response.Error(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error.", errors);
        }
    }
}
