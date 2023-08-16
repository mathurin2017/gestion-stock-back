package com.lloufa.gestionstockback.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ArticleDto {

    private Integer id;

    private String code;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategoryDto categoryDto;

    private List<LigneVenteDto> ligneVenteDtos;

    private List<LigneCommandeClientDto> ligneCommandeClientDtos;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurDtos;

    private List<MvtStkDto> mvtStkDtos;

}
