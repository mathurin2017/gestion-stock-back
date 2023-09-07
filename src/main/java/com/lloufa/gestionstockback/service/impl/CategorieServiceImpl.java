package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.CategoryDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.mapping.CategorieMapping;
import com.lloufa.gestionstockback.model.Category;
import com.lloufa.gestionstockback.repository.CategorieRepository;
import com.lloufa.gestionstockback.service.ArticleService;
import com.lloufa.gestionstockback.service.CategorieService;
import com.lloufa.gestionstockback.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    private final ArticleService articleService;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository, ArticleService articleService) {
        this.categorieRepository = categorieRepository;
        this.articleService = articleService;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategorieValidator.validate(categoryDto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", categoryDto);
            throw new InvalidEntityException("La catégorie n'est pas valide ", ErrorCode.CATEGORY_NOT_VALID, errors);
        }
        Category category = this.categorieRepository.save(CategorieMapping.toEntity(categoryDto));

        return CategorieMapping.fromEntity(category);
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (null == id) {
            log.error("Category ID is null");
            return null;
        }

        return this.categorieRepository.findById(id)
                .map(CategorieMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune catégorie avec ID " + id + " n'a été trouvée dans la BDD", ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (null == code) {
            log.error("Category CODE is null");
            return null;
        }

        return this.categorieRepository.findCategoryByCode(code)
                .map(CategorieMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune catégorie avec le CODE " + code + " n'a été trouvée dans la BDD", ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categoryList = this.categorieRepository.findAll();
        log.error("Number category in BDD is {}", categoryList.size());
        return categoryList.stream().map(CategorieMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);

        Optional.ofNullable(this.articleService.findAllArticleByIdCategorie(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer une catégorie déjà utiliser dans des articles", ErrorCode.CATEGORY_ALREADY_IN_USE));

        this.categorieRepository.deleteById(id);
    }

}
