package com.lloufa.gestionstockback.dto;

import com.lloufa.gestionstockback.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Integer id;
    private String code;
    private EtatCommande etatCommande;
    private Instant dateCommande;
    private FournisseurDto fournisseurDto;
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurDtos;

}
