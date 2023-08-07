package com.lloufa.gestionstockback.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String motDePasse;

    private AdresseDto adresseDto;

    private String photo;

    private EntrepriseDto entrepriseDto;

    private List<RoleDto> roleDtos;
}
