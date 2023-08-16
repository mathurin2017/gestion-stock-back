package com.lloufa.gestionstockback.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresseDto;

    private String email;

    private String numeroTelephone;

    private String photo;

    private List<CommandeClientDto> commandeClientDtos;

}
