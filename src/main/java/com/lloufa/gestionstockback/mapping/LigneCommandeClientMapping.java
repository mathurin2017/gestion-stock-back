package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.model.LigneCommandeClient;

public class LigneCommandeClientMapping {

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
        if (null == ligneCommandeClient) {
            return null;
            // TODO throw an exeption
        }

        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .quantite(ligneCommandeClient.getQuantite())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .articleDto(ArticleMapping.fromEntity(ligneCommandeClient.getArticle()))
                .idEntreprise(ligneCommandeClient.getIdEntreprise())
                .commandeClientDto(CommandeClientMapping.fromEntity(ligneCommandeClient.getCommandeClient()))
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
        if (null == ligneCommandeClientDto) {
            return null;
            // TODO throw an exeption
        }

        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(ligneCommandeClientDto.getId());
        ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
        ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());
        ligneCommandeClient.setArticle(ArticleMapping.toEntity(ligneCommandeClientDto.getArticleDto()));
        ligneCommandeClient.setIdEntreprise(ligneCommandeClientDto.getIdEntreprise());
        ligneCommandeClient.setCommandeClient(CommandeClientMapping.toEntity(ligneCommandeClientDto.getCommandeClientDto()));

        return ligneCommandeClient;
    }

}
