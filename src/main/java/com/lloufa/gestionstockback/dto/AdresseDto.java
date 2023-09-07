package com.lloufa.gestionstockback.dto;

import lombok.*;

@Data
@Builder
public class AdresseDto {

    private Integer id;
    private String adresse1;
    private String adresse2;
    private String ville;
    private String codePostal;
    private String pays;

}
