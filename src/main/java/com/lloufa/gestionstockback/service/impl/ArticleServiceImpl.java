package com.lloufa.gestionstockback.service.impl;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.dto.LigneVenteDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.mapping.ArticleMapping;
import com.lloufa.gestionstockback.model.Article;
import com.lloufa.gestionstockback.repository.ArticleRepository;
import com.lloufa.gestionstockback.service.*;
import com.lloufa.gestionstockback.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final LigneVenteService ligneVenteService;
    private final LigneCommandeClientService ligneCommandeClientService;
    private final LigneCommandeFournisseurService ligneCommandeFournisseurService;
    private final PhotoService photoService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, LigneVenteService ligneVenteService, LigneCommandeClientService ligneCommandeClientService, LigneCommandeFournisseurService ligneCommandeFournisseurService, PhotoService photoService) {
        this.articleRepository = articleRepository;
        this.ligneVenteService = ligneVenteService;
        this.ligneCommandeClientService = ligneCommandeClientService;
        this.ligneCommandeFournisseurService = ligneCommandeFournisseurService;
        this.photoService = photoService;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", articleDto);
            throw new InvalidEntityException("L'article n'est pas valide ", ErrorCode.ARTICLE_NOT_VALID, errors);
        }
        Article article = this.articleRepository.save(ArticleMapping.toEntity(articleDto));

        return ArticleMapping.fromEntity(article);
    }

    @Override
    public ArticleDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        ArticleDto articleDto = this.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'article", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        articleDto.setPhoto(urlPhoto);

        return this.save(articleDto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (null == id) {
            log.error("Article ID is null");
            return null;
        }

        return this.articleRepository.findById(id)
                .map(ArticleMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun article avec ID " + id + " n'a été trouvé dans la BDD", ErrorCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCode(String code) {
        if (null == code) {
            log.error("Article CODE is null");
            return null;
        }

        return this.articleRepository.findArticleByCode(code)
                .map(ArticleMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun article avec le CODE " + code + " n'a été trouvé dans la BDD", ErrorCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        List<Article> articleList = this.articleRepository.findAll();
        log.error("Number article in BDD is {}", articleList.size());
        return articleList.stream().map(ArticleMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVente(Integer idArticle) {
        return this.ligneVenteService.findAllLigneVenteByArticleId(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return this.ligneCommandeClientService.findAllLigneCommandeClientByArticleId(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return this.ligneCommandeFournisseurService.findAllLigneCommandeFournisseurByArticleId(idArticle);
    }

    @Override
    public List<ArticleDto> findAllArticleByIdCategorie(Integer idCategory) {
        List<Article> articleList = this.articleRepository.findAllByCategoryId(idCategory);
        log.error("Number article by category in BDD is {}", articleList.size());
        return articleList.stream().map(ArticleMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);

        Optional.ofNullable(this.ligneCommandeClientService.findAllLigneCommandeClientByArticleId(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer un article déjà utiliser dans des commandes clients", ErrorCode.ARTICLE_ALREADY_IN_USE));

        Optional.ofNullable(this.ligneCommandeFournisseurService.findAllLigneCommandeFournisseurByArticleId(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer un article déjà utiliser dans des commandes fournisseurs", ErrorCode.ARTICLE_ALREADY_IN_USE));

        Optional.ofNullable(this.ligneVenteService.findAllLigneVenteByArticleId(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer un article déjà utiliser dans des lignes ventes", ErrorCode.ARTICLE_ALREADY_IN_USE));

        // List<LigneCommandeClientDto> ligneCommandeClientDtoList = this.ligneCommandeClientService.findAllByArticleId(id);
        // if (!ligneCommandeClientDtoList.isEmpty()) throw new InvalidOperationException("Impossible de supprimer un article déjà utiliser dans des commandes clients", ErrorCode.ARTICLE_ALREADY_IN_USE);

        this.articleRepository.deleteById(id);
    }

}
