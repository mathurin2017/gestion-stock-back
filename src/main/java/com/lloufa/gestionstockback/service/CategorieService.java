package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.CategoryDto;

import java.util.List;

public interface CategorieService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findById(Integer id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    void delete(Integer id);

}
