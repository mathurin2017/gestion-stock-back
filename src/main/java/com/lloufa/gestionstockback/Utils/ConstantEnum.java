package com.lloufa.gestionstockback.Utils;

import lombok.Getter;

@Getter
public enum ConstantEnum {

    ID_ENNTREPRISE("idEntreprise"),
    AUTHORIZATION_HEADER("Authorization");

    private final String value;

    ConstantEnum(String value) {
        this.value = value;
    }

}
