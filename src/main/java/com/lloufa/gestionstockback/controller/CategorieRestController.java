package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CategorieApi;
import com.lloufa.gestionstockback.dto.CategoryDto;
import com.lloufa.gestionstockback.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieRestController implements CategorieApi {

    private final CategorieService categorieService;

    @Autowired
    public CategorieRestController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Override
    public ResponseEntity<CategoryDto> save(CategoryDto categoryDto) {
        return ResponseEntity.ok(this.categorieService.save(categoryDto));
    }

    @Override
    public ResponseEntity<CategoryDto> update(CategoryDto categoryDto) {
        return ResponseEntity.ok(this.categorieService.save(categoryDto));
    }

    @Override
    public ResponseEntity<CategoryDto> findById(Integer id) {
        return ResponseEntity.ok(this.categorieService.findById(id));
    }

    @Override
    public ResponseEntity<CategoryDto> findByCode(String code) {
        return ResponseEntity.ok(this.categorieService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(this.categorieService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.categorieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
