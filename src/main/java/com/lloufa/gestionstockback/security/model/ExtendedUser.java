package com.lloufa.gestionstockback.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class ExtendedUser extends User {

    private final Integer idEntreprise;

    public ExtendedUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer idEntreprise) {
        super(username, password, authorities);
        this.idEntreprise = idEntreprise;
    }

}
