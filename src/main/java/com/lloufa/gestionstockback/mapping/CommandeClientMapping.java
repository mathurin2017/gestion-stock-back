package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.CommandeClientDto;
import com.lloufa.gestionstockback.model.CommandeClient;
import com.lloufa.gestionstockback.model.EtatCommande;

public class CommandeClientMapping {

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
        if (null == commandeClient) {
            return null;
            // TODO throw an execption
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .etatCommande(commandeClient.getEtatCommande())
                .dateCommande(commandeClient.getDateCommande())
                .clientDto(ClientMapping.fromEntity(commandeClient.getClient()))
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
        if (null == commandeClientDto) {
            return null;
            // TODO throw an execption
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setClient(ClientMapping.toEntity(commandeClientDto.getClientDto()));

        return commandeClient;
    }

    public static boolean isCommandeLivree(CommandeClientDto commandeClientDto) {
        return commandeClientDto.getEtatCommande().equals(EtatCommande.LIVREE);
    }

}
