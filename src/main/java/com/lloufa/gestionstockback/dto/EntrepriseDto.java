package com.lloufa.gestionstockback.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EntrepriseDto {

    private Integer id;

    private String nom;

    private String description;

    private AdresseDto adresseDto;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numeroTelephone;

    private String siteWeb;

    private List<UtilisateurDto> utilisateurDtos;

}
