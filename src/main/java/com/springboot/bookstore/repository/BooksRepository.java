package com.springboot.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.bookstore.model.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {
    /** Find specific resource by category */
    @Query(value = "SELECT * FROM books as book WHERE book.category=?1 LIMIT 1", nativeQuery = true)
    Optional<Books> findOneByCategory(Long categoty);
}
