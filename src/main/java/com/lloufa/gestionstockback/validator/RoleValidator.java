package com.lloufa.gestionstockback.validator;

import com.lloufa.gestionstockback.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    public static List<String> validate(RoleDto roleDto) {
        List<String> errors = new ArrayList<>();

        if (null == roleDto) errors.add("Veillez renseigner les champs du role");
        else if (!StringUtils.hasLength(roleDto.getNom())) errors.add("Veillez renseigner le nom du role");

        return errors;
    }

}
