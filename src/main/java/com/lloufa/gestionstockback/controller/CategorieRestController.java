package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CategorieApi;
import com.lloufa.gestionstockback.dto.CategoryDto;
import com.lloufa.gestionstockback.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieRestController implements CategorieApi {

    private final CategorieService categorieService;

    @Autowired
    public CategorieRestController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    public CategoryDto save(CategoryDto categoryDto) {
        return this.categorieService.save(categoryDto);
    }

    public CategoryDto update(CategoryDto categoryDto) {
        return this.categorieService.save(categoryDto);
    }

    public CategoryDto findById(Integer id) {
        return this.categorieService.findById(id);
    }

    public CategoryDto findByCode(String code) {
        return this.categorieService.findByCode(code);
    }

    public List<CategoryDto> findAll() {
        return this.categorieService.findAll();
    }

    public void delete(Integer id) {
        this.categorieService.delete(id);
    }
}
