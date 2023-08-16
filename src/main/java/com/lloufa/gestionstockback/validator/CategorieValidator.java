package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {

    public static List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (null == categoryDto || !StringUtils.hasLength(categoryDto.getCode())) errors.add("Veillez renseigner le code de la catégorie");

        return errors;
    }

}
