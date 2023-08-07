package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.AdresseDto;
import com.lloufa.gestionstockback.model.Adresse;

public class AdresseMapping {

    public static AdresseDto fromEntity(Adresse adresse) {
        if (null == adresse) {
            return null;
            // TODO throw an execption
        }

        return AdresseDto.builder()
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .codePostal(adresse.getCodePostal())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .build();
    }

    public static Adresse toEntity(AdresseDto adresseDto) {
        if (null == adresseDto) {
            return null;
            // TODO throw an execption
        }

        Adresse adresse = new Adresse();
        adresse.setAdresse1(adresseDto.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setCodePostal(adresseDto.getCodePostal());
        adresse.setVille(adresseDto.getVille());
        adresse.setPays(adresseDto.getPays());

        return adresse;
    }
}
