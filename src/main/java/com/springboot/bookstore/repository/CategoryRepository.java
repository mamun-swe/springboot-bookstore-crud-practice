package com.springboot.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookstore.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
