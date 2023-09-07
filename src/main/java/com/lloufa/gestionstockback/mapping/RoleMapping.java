package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.RoleDto;
import com.lloufa.gestionstockback.model.Role;

public class RoleMapping {

    public static RoleDto fromEntity(Role role) {
        if (null == role) {
            return null;
            // TODO throw an execption
        }

        return RoleDto.builder()
                .id(role.getId())
                .nom(role.getNom())
                // .utilisateurDto(UtilisateurMapping.fromEntity(role.getUtilisateur()))
                .build();
    }

    public static Role toEntity(RoleDto roleDto) {
        if (null == roleDto) {
            return null;
            // TODO throw an execption
        }

        Role role = new Role();
        role.setId(roleDto.getId());
        role.setNom(roleDto.getNom());
        // role.setUtilisateur(UtilisateurMapping.toEntity(roleDto.getUtilisateurDto()));

        return role;
    }

}
