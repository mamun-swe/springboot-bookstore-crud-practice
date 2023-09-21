package com.springboot.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.bookstore.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /** Find specific resource by name */
    @Query(value = "SELECT * FROM categories as cat WHERE cat.name=?1", nativeQuery = true)
    Optional<Category> findOneByName(String name);

    /** Find specific resource by name where id isn't given id */
    @Query(value = "SELECT * FROM categories as cat WHERE cat.id!=?1 AND cat.name=?2", nativeQuery = true)
    Optional<Category> findOneByNameWhereIdNotEqual(Long id, String name);
}
