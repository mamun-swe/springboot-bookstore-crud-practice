package com.springboot.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bookstore.model.Category;
import com.springboot.bookstore.model.dtos.CategoryDto;
import com.springboot.bookstore.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /** get all categories record */
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();

        this.categoryRepository.findAll().forEach(item -> categories.add(item));
        return categories;
    }

    /** getting a specific record by id */
    public Optional<Category> geCategoryById(Long id) {
        return this.categoryRepository.findById(id);
    }

    /** cerate a specific record */
    public void createCategory(Category documents) {
        this.categoryRepository.save(documents);
    }

    /** update a specific record */
    public void updateCategory(CategoryDto documents, Long id) {
        Category category = this.categoryRepository.findById(id).get();
        category.setName(documents.getName());

        this.categoryRepository.save(category);
    }

    /** delete a specific record */
    public void destroyCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
