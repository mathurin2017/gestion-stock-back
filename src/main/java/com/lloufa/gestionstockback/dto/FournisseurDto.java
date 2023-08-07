package com.lloufa.gestionstockback.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresseDto;

    private String email;

    private String numeroTelephone;

    private String photo;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurDtos;
}
