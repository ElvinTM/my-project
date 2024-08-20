package org.example.repository;

import org.example.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT e FROM Category e WHERE e.name LIKE %:name%")
    Optional<Category> findAllByNameContaining(@Param("name") String name);
}
