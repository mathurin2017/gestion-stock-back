package com.lloufa.gestionstockback.security.controller.api;

import com.lloufa.gestionstockback.security.dto.AuthenticationRequest;
import com.lloufa.gestionstockback.security.dto.AuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("authenticate-api")
@RequestMapping("/authenticate")
public interface AuthentificationApi {

    @PostMapping
    @ApiOperation(value = "Authentification token", notes = "Cette méthode d'authentifier et de recupérer un token", response = AuthenticationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet authenticationRequest est crée"),
            @ApiResponse(code = 400, message = "L'objet authenticationResponse n'est pas valide")
    })
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}
