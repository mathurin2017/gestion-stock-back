package com.lloufa.gestionstockback.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;
    private BigDecimal quantite;
    private BigDecimal prixUnitaire;
    private String idEntreprise;
    private VenteDto venteDto;
    private ArticleDto articleDto;

}
