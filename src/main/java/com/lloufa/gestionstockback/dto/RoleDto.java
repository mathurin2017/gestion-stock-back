package com.lloufa.gestionstockback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Integer id;

    private String nom;

    private UtilisateurDto utilisateurDto;
}
