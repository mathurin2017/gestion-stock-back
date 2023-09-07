package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.VenteDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validate(VenteDto venteDto) {
        List<String> errors = new ArrayList<>();

        if (null == venteDto) {
            errors.add("Veillez renseigner les champs obligatoire de la vente");
        } else {
            if (!StringUtils.hasLength(venteDto.getCode())) errors.add("Veillez renseigner le code de la vente");
            if (null == venteDto.getDateVente()) errors.add("Veillez renseigner la date de la vente");
        }

        return errors;
    }

}
