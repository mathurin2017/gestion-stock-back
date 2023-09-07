package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import com.lloufa.gestionstockback.model.CommandeFournisseur;
import com.lloufa.gestionstockback.model.EtatCommande;

public class CommandeFournisseurMapping {

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (null == commandeFournisseur) {
            return null;
            // TODO throw an execption
        }

        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseurDto(FournisseurMapping.fromEntity(commandeFournisseur.getFournisseur()))
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
        if (null == commandeFournisseurDto) {
            return null;
            // TODO throw an execption
        }

        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setCode(commandeFournisseurDto.getCode());
        commandeFournisseur.setEtatCommande(commandeFournisseurDto.getEtatCommande());
        commandeFournisseur.setDateCommande(commandeFournisseurDto.getDateCommande());
        commandeFournisseur.setFournisseur(FournisseurMapping.toEntity(commandeFournisseurDto.getFournisseurDto()));

        return commandeFournisseur;
    }

    public static boolean isCommandeLivree(CommandeFournisseurDto commandeFournisseurDto) {
        return commandeFournisseurDto.getEtatCommande().equals(EtatCommande.LIVREE);
    }

}
