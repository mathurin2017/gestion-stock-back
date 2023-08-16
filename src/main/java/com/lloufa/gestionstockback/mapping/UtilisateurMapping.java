package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.model.Utilisateur;

import java.util.stream.Collectors;

public class UtilisateurMapping {

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
        if (null == utilisateur) {
            return null;
            // TODO throw an execption
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .motDePasse(utilisateur.getMotDePasse())
                .adresseDto(AdresseMapping.fromEntity(utilisateur.getAdresse()))
                .photo(utilisateur.getPhoto())
                .entrepriseDto(EntrepriseMapping.fromEntity(utilisateur.getEntreprise()))
                .roleDtos(null != utilisateur.getRoles() ? utilisateur.getRoles().stream().map(RoleMapping::fromEntity).collect(Collectors.toList()) : null)
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (null == utilisateurDto) {
            return null;
            // TODO throw an execption
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setAdresse(AdresseMapping.toEntity(utilisateurDto.getAdresseDto()));
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEntreprise(EntrepriseMapping.toEntity(utilisateurDto.getEntrepriseDto()));
        utilisateur.setRoles(null != utilisateurDto.getRoleDtos() ? utilisateurDto.getRoleDtos().stream().map(RoleMapping::toEntity).collect(Collectors.toList()) : null);

        return utilisateur;
    }

}
