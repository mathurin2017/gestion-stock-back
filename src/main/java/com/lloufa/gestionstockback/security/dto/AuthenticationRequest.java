package com.lloufa.gestionstockback.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    private String login;

    private String password;

}
