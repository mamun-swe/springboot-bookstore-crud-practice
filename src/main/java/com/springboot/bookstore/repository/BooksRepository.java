package com.springboot.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bookstore.model.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {

}
