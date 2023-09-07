package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (null == commandeFournisseurDto) {
            errors.add("Veillez renseigner les champs obligatoire de la commande du fournisseur");
        } else {
            if (!StringUtils.hasLength(commandeFournisseurDto.getCode())) errors.add("Veillez renseigner le nom de la commande");
            if (null == commandeFournisseurDto.getDateCommande()) errors.add("Veillez renseigner la date de la commande");
            if (!StringUtils.hasLength(commandeFournisseurDto.getEtatCommande().toString())) errors.add("Veillez renseigner l'état de la commande");
            if (null == commandeFournisseurDto.getFournisseurDto() || null == commandeFournisseurDto.getFournisseurDto().getId()) errors.add("Veillez renseigner le fournisseur de la commande");
        }

        return errors;
    }

}
