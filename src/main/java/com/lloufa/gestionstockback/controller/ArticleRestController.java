package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.ArticleApi;
import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleRestController implements ArticleApi {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    public ArticleDto save(ArticleDto articleDto) {
        return this.articleService.save(articleDto);
    }

    public ArticleDto update(ArticleDto articleDto) {
        return this.articleService.save(articleDto);
    }

    public ArticleDto findById(Integer id) {
        return this.articleService.findById(id);
    }

    public ArticleDto findByCode(String code) {
        return this.articleService.findByCode(code);
    }

    public List<ArticleDto> findAll() {
        return this.articleService.findAll();
    }

    public void delete(Integer id) {
        this.articleService.delete(id);
    }
}
