package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if (null == utilisateurDto) {
            errors.add("Veillez renseigner les champs de l'utilisateur");
        } else {
            if (!StringUtils.hasLength(utilisateurDto.getNom())) errors.add("Veillez renseigner le nom de l'utilisateur");
            if (!StringUtils.hasLength(utilisateurDto.getPrenom())) errors.add("Veillez renseigner le prénom de l'utilisateur");
            if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) errors.add("Veillez renseigner le mot de passe de l'utilisateur");
            if (null == utilisateurDto.getDateDeNaissance()) errors.add("Veillez renseigner la date de l'utilisateur");
            if (null == utilisateurDto.getAdresseDto()) {
                errors.add("Veillez renseigner l'adresse de l'utilisateur");
            } else {
                errors.addAll(UtilsValidator.validateAdresse(utilisateurDto.getAdresseDto()));
            }
        }

        return errors;
    }

}
