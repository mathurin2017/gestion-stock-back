package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList<>();

        if (null == commandeClientDto) {
            errors.add("Veillez renseigner les champs obligatoire de la commande du client");
        } else {
            if (!StringUtils.hasLength(commandeClientDto.getCode())) errors.add("Veillez renseigner le nom de la commande");
            if (null == commandeClientDto.getDateCommande()) errors.add("Veillez renseigner la date de la commande");
            if (!StringUtils.hasLength(commandeClientDto.getEtatCommande().toString())) errors.add("Veillez renseigner l'état de la commande");
            if (null == commandeClientDto.getClientDto() || null == commandeClientDto.getClientDto().getId()) errors.add("Veillez renseigner le client de la commande");
        }

        return errors;
    }

}
