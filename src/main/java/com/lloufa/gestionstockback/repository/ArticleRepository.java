package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findArticleByCode(String code);

}
