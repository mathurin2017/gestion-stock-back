package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (null == fournisseurDto) {
            errors.add("Veillez renseigner les champs du fournisseur");
        } else {
            if (!StringUtils.hasLength(fournisseurDto.getNom())) errors.add("Veillez renseigner le nom du fournisseur");
            if (!StringUtils.hasLength(fournisseurDto.getPrenom())) errors.add("Veillez renseigner le prénom du fournisseur");
            if (null == fournisseurDto.getEmail()) errors.add("Veillez renseigner l'email du fournisseur");
            if (null == fournisseurDto.getNumeroTelephone()) errors.add("Veillez renseigner le numéro de téléphone du fournisseur");
        }

        return errors;
    }
}
