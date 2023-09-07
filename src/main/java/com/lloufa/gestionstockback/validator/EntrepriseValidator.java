package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();

        if (null == entrepriseDto) {
            errors.add("Veillez renseigner les champs obligatoire de l'entreprise");
        } else {
            if (!StringUtils.hasLength(entrepriseDto.getNom())) errors.add("Veillez renseigner le nom de l'entreprise");
            if (!StringUtils.hasLength(entrepriseDto.getDescription())) errors.add("Veillez renseigner la description de l'entreprise");
            if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) errors.add("Veillez renseigner le code fiscal de l'entreprise");
            if (!StringUtils.hasLength(entrepriseDto.getEmail())) errors.add("Veillez renseigner l'email de l'entreprise");
            if (!StringUtils.hasLength(entrepriseDto.getNumeroTelephone())) errors.add("Veillez renseigner le numéro de téléphone de l'entreprise");
            errors.addAll(AdresseValidator.validate(entrepriseDto.getAdresseDto()));
        }

        return errors;
    }

}
