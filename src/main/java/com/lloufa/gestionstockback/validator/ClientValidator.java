package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if (null == clientDto) {
            errors.add("Veillez renseigner les champs du client");
        } else {
            if (!StringUtils.hasLength(clientDto.getNom())) errors.add("Veillez renseigner le nom du client");
            if (!StringUtils.hasLength(clientDto.getPrenom())) errors.add("Veillez renseigner le prénom du client");
            if (null == clientDto.getEmail()) errors.add("Veillez renseigner l'email du client");
            if (null == clientDto.getNumeroTelephone()) errors.add("Veillez renseigner le numéro de téléphone du client");
        }

        return errors;
    }
}
