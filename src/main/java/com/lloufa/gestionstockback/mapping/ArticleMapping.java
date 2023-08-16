package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.model.Article;

public class ArticleMapping {

    public static ArticleDto fromEntity(Article article) {
        if (null == article) {
            return null;
            // TODO throw an execption
        }

        return ArticleDto.builder()
                .id(article.getId())
                .code(article.getCode())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .categoryDto(CategorieMapping.fromEntity(article.getCategory()))
                .build();
    }

    public static Article toEntity(ArticleDto articleDto) {
        if (null == articleDto) {
            return null;
            // TODO throw an execption
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setCode(articleDto.getCode());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setCategory(CategorieMapping.toEntity(articleDto.getCategoryDto()));

        return article;
    }

}
