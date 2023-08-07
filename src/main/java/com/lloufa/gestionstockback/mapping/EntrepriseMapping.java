package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.EntrepriseDto;
import com.lloufa.gestionstockback.model.Entreprise;

public class EntrepriseMapping {

    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (null == entreprise) {
            return null;
            // TODO throw an execption
        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
                .adresseDto(AdresseMapping.fromEntity(entreprise.getAdresse()))
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .email(entreprise.getEmail())
                .numeroTelephone(entreprise.getNumeroTelephone())
                .siteWeb(entreprise.getSiteWeb())
                .build();
    }

    public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
        if (null == entrepriseDto) {
            return null;
            // TODO throw an execption
        }

        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setAdresse(AdresseMapping.toEntity(entrepriseDto.getAdresseDto()));
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setEmail(entrepriseDto.getEmail());
        entreprise.setNumeroTelephone(entrepriseDto.getNumeroTelephone());
        entreprise.setSiteWeb(entrepriseDto.getSiteWeb());

        return entreprise;
    }
}
