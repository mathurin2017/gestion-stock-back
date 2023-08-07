package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.LigneVenteDto;
import com.lloufa.gestionstockback.model.LigneVente;

public class LigneVenteMapping {

    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        if (null == ligneVente) {
            return null;
            // TODO throw an execption
        }

        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .venteDto(VenteMapping.fromEntity(ligneVente.getVente()))
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        if (null == ligneVenteDto) {
            return null;
            // TODO throw an execption
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setVente(VenteMapping.toEntity(ligneVenteDto.getVenteDto()));

        return ligneVente;
    }
}
