package com.lloufa.gestionstockback.Utils;

import lombok.Getter;

@Getter
public enum ConstantEnumUtils {

    ID_ENTREPRISE("id_entreprise"),
    AUTHORIZATION_HEADER("Authorization");

    private final String value;

    ConstantEnumUtils(String value) {
        this.value = value;
    }

}
