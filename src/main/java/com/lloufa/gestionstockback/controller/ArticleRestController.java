package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.ArticleApi;
import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleRestController implements ArticleApi {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    public ResponseEntity<ArticleDto> save(ArticleDto articleDto) {
        return ResponseEntity.ok(this.articleService.save(articleDto));
    }

    public ResponseEntity<ArticleDto> update(ArticleDto articleDto) {
        return ResponseEntity.ok(this.articleService.save(articleDto));
    }

    public ResponseEntity<ArticleDto> findById(Integer id) {
        return ResponseEntity.ok(this.articleService.findById(id));
    }

    public ResponseEntity<ArticleDto> findByCode(String code) {
        return ResponseEntity.ok(this.articleService.findByCode(code));
    }

    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(this.articleService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
