package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.VenteDto;
import com.lloufa.gestionstockback.model.Vente;

public class VenteMapping {

    public static VenteDto fromEntity(Vente vente) {
        if (null == vente) {
            return null;
            // TODO throw an exception
        }

        return VenteDto.builder()
                .id(vente.getId())
                .code(vente.getCode())
                .dateVente(vente.getDateVente())
                .commentaire(vente.getCommentaire())
                .build();
    }

    public static Vente toEntity(VenteDto venteDto) {
        if (null == venteDto) {
            return null;
            // TODO throw an exception
        }

        Vente vente = new Vente();
        vente.setId(venteDto.getId());
        vente.setCode(venteDto.getCode());
        vente.setDateVente(venteDto.getDateVente());
        vente.setCommentaire(venteDto.getCommentaire());

        return vente;
    }
}
