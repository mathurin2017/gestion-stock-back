package com.lloufa.gestionstockback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordUserDto {

    private Integer id;
    private String motDePasse;
    private String confirmMotDePasse;

}
