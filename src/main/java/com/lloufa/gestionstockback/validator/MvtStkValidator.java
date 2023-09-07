package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.MvtStkDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {

    public static List<String> validate(MvtStkDto mvtStkDto) {
        List<String> errors = new ArrayList<>();

        if (null == mvtStkDto) {
            errors.add("Veillez renseigner les champs obligatoire du mvtStk");
        } else {
            if (!StringUtils.hasLength(mvtStkDto.getDateMvt().toString())) errors.add("Veillez renseigner la date du mvtStk");
            if (null == mvtStkDto.getQuantite() || 0 == mvtStkDto.getQuantite().compareTo(BigDecimal.ZERO)) errors.add("Veillez renseigner la quantité du mvtStk");
            if (null == mvtStkDto.getArticleDto() || null == mvtStkDto.getArticleDto().getId()) errors.add("Veillez renseigner l'article du mvtStk");
            if (!StringUtils.hasLength(mvtStkDto.getTypeMvtStk().name())) errors.add("Veillez renseigner le type du mvtStk");
        }

        return errors;
    }

}
