package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if (null == articleDto) {
            errors.add("Veillez renseigner les champs obligatoire de l'article");
        } else {
            if (!StringUtils.hasLength(articleDto.getCode())) errors.add("Veillez renseigner le code de l'article");
            if (!StringUtils.hasLength(articleDto.getDesignation())) errors.add("Veillez renseigner la désignation de l'article");
            if (null == articleDto.getPrixUnitaireHt()) errors.add("Veillez renseigner le prix unitaire HT de l'article");
            if (null == articleDto.getPrixUnitaireTtc()) errors.add("Veillez renseigner le prix unitaire TTC de l'article");
            if (null == articleDto.getTauxTva()) errors.add("Veillez renseigner le taux TVA de l'article");
            if (null == articleDto.getCategoryDto()) errors.add("Veillez sélectionner une catégorie");
        }

        return errors;
    }

}
