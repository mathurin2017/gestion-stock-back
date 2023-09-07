package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.model.LigneCommandeFournisseur;

public class LigneCommandeFournisseurMapping {

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (null == ligneCommandeFournisseur) {
            return null;
            // TODO throw an execption
        }

        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .idEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .articleDto(ArticleMapping.fromEntity(ligneCommandeFournisseur.getArticle()))
                .commandeFournisseurDto(CommandeFournisseurMapping.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        if (null == ligneCommandeFournisseurDto) {
            return null;
            // TODO throw an execption
        }

        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
        ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
        ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());
        ligneCommandeFournisseur.setArticle(ArticleMapping.toEntity(ligneCommandeFournisseurDto.getArticleDto()));
        ligneCommandeFournisseur.setIdEntreprise(ligneCommandeFournisseurDto.getIdEntreprise());
        ligneCommandeFournisseur.setCommandeFournisseur(CommandeFournisseurMapping.toEntity(ligneCommandeFournisseurDto.getCommandeFournisseurDto()));

        return ligneCommandeFournisseur;
    }

}
