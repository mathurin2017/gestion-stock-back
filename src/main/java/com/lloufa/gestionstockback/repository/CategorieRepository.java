package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByCode(String code);
}
