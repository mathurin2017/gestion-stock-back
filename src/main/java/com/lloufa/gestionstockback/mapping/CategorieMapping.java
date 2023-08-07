package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.CategoryDto;
import com.lloufa.gestionstockback.model.Category;

public class CategorieMapping {

    public static CategoryDto fromEntity(Category category) {
        if (null == category) {
            return null;
            // TODO throw an execption
        }

        return CategoryDto.builder()
                .id(category.getId())
                .code(category.getCode())
                .designation(category.getDesignation())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if (null == categoryDto) {
            return null;
            // TODO throw an execption
        }

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setCode(categoryDto.getCode());
        category.setDesignation(categoryDto.getDesignation());

        return category;
    }
}
