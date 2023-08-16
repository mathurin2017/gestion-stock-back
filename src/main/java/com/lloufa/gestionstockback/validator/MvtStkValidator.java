package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.dto.VenteDto;

import java.util.ArrayList;
import java.util.List;

public class MvtStkValidator {

    public static List<String> validate(MvtStkDto mvtStkDto) {
        List<String> errors = new ArrayList<>();

        /*if (null == commandeClientDto) {
            errors.add("Veillez renseigner les champs de la commande du client");
        } else {
            if (!StringUtils.hasLength(commandeClientDto.getCode())) errors.add("Veillez renseigner le nom du client");
            if (!StringUtils.hasLength(commandeClientDto.getPrenom())) errors.add("Veillez renseigner le prénom du client");
            if (null == commandeClientDto.getEmail()) errors.add("Veillez renseigner l'email du client");
            if (null == commandeClientDto.getNumeroTelephone()) errors.add("Veillez renseigner le numéro de téléphone du client");
        }*/

        return errors;
    }

}
