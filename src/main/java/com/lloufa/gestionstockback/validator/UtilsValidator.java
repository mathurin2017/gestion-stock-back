package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.AdresseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilsValidator {

    public static List<String> validateAdresse(AdresseDto adresseDto) {
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasLength(adresseDto.getAdresse1())) errors.add("Le champ adresse 1 est obligatoire");
        if (!StringUtils.hasLength(adresseDto.getVille())) errors.add("Le champ ville est obligatoire");
        if (!StringUtils.hasLength(adresseDto.getCodePostal())) errors.add("Le champ code postal est obligatoire");
        if (!StringUtils.hasLength(adresseDto.getPays())) errors.add("Le champ pays est obligatoire");

        return errors;
    }
}
