package com.lloufa.gestionstockback.security.controller;

import com.lloufa.gestionstockback.security.dto.AuthenticationRequest;
import com.lloufa.gestionstockback.security.dto.AuthenticationResponse;
import com.lloufa.gestionstockback.security.model.ExtendedUser;
import com.lloufa.gestionstockback.security.service.ApplicationUserDetailsService;
import com.lloufa.gestionstockback.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthentificationRestController {

    private final AuthenticationManager authenticationManager;

    private final ApplicationUserDetailsService applicationUserDetailsService;

    private final JwtService jwtService;

    @Autowired
    public AuthentificationRestController(AuthenticationManager authenticationManager, ApplicationUserDetailsService applicationUserDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        final ExtendedUser userDetails = this.applicationUserDetailsService.loadUserByUsername(request.getLogin());
        final String generateToken = this.jwtService.generateToken(userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(generateToken).build());
    }

}
