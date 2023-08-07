package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.dto.FournisseurDto;
import com.lloufa.gestionstockback.model.Fournisseur;

public class FournisseurMapping {

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (null == fournisseur) {
            return null;
            // TODO throw an execption
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresseDto(AdresseMapping.fromEntity(fournisseur.getAdresse()))
                .email(fournisseur.getEmail())
                .numeroTelephone(fournisseur.getNumeroTelephone())
                .photo(fournisseur.getPhoto())
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if (null == fournisseurDto) {
            return null;
            // TODO throw an execption
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setAdresse(AdresseMapping.toEntity(fournisseurDto.getAdresseDto()));
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setNumeroTelephone(fournisseurDto.getNumeroTelephone());
        fournisseur.setPrenom(fournisseurDto.getPrenom());

        return fournisseur;
    }
}
