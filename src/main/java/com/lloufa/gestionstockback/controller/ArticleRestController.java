package com.lloufa.gestionstockback.controller;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.controller.api.ArticleApi;
import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.dto.LigneVenteDto;
import com.lloufa.gestionstockback.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ArticleRestController implements ArticleApi {

    private final ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ArticleDto> save(ArticleDto articleDto) {
        return ResponseEntity.ok(this.articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> savePhoto(Integer id, MultipartFile photo, String title) throws FlickrException, IOException {
        return ResponseEntity.ok(this.articleService.savePhoto(id, photo.getInputStream(), title));
    }

    @Override
    public ResponseEntity<ArticleDto> update(ArticleDto articleDto) {
        return ResponseEntity.ok(this.articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> findById(Integer id) {
        return ResponseEntity.ok(this.articleService.findById(id));
    }

    @Override
    public ResponseEntity<ArticleDto> findByCode(String code) {
        return ResponseEntity.ok(this.articleService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAll() {
        return ResponseEntity.ok(this.articleService.findAll());
    }

    @Override
    public ResponseEntity<List<ArticleDto>> findAllArticleByIdCategorie(Integer idCategory) {
        return ResponseEntity.ok(this.articleService.findAllArticleByIdCategorie(idCategory));
    }

    @Override
    public ResponseEntity<List<LigneVenteDto>> findHistoriqueVente(Integer idArticle) {
        return ResponseEntity.ok(this.articleService.findHistoriqueVente(idArticle));
    }

    @Override
    public ResponseEntity<List<LigneCommandeClientDto>> findHistoriqueCommandeClient(Integer idArticle) {
        return ResponseEntity.ok(this.articleService.findHistoriqueCommandeClient(idArticle));
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return ResponseEntity.ok(this.articleService.findHistoriqueCommandeFournisseur(idArticle));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
