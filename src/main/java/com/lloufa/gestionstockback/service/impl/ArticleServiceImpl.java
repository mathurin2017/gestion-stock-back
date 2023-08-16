package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.ArticleMapping;
import com.lloufa.gestionstockback.model.Article;
import com.lloufa.gestionstockback.repository.ArticleRepository;
import com.lloufa.gestionstockback.service.ArticleService;
import com.lloufa.gestionstockback.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
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
    public void delete(Integer id) {
        ArticleDto articleDto = findById(id);
        if (null != articleDto) this.articleRepository.delete(ArticleMapping.toEntity(articleDto));
    }

}
