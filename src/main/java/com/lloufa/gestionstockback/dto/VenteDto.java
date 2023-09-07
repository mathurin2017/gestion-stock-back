package com.lloufa.gestionstockback.dto;

import com.lloufa.gestionstockback.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VenteDto {

    private Integer id;
    private String code;
    private Instant dateVente;
    private String commentaire;
    private List<LigneVenteDto> ligneVenteDtos;

}
