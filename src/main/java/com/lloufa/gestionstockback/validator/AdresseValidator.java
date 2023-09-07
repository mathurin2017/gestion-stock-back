package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.AdresseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AdresseValidator {

    public static List<String> validate(AdresseDto adresseDto) {
        List<String> errors = new ArrayList<>();

        if (null == adresseDto) {
            errors.add("Veillez renseigner les champs obligatoire de l'adresse");
        } else {
            if (!StringUtils.hasLength(adresseDto.getAdresse1())) errors.add("Veillez renseigner l'adresse 1");
            if (!StringUtils.hasLength(adresseDto.getVille())) errors.add("Veillez renseigner la ville");
            if (!StringUtils.hasLength(adresseDto.getCodePostal())) errors.add("Veillez renseigner le code postal");
            if (!StringUtils.hasLength(adresseDto.getPays())) errors.add("Veillez renseigner le pays");
        }

        return errors;
    }

}
