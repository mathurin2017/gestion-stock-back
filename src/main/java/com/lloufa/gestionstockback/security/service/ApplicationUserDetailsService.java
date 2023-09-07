package com.lloufa.gestionstockback.security.service;

import com.lloufa.gestionstockback.dto.RoleDto;
import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.security.model.ExtendedUser;
import com.lloufa.gestionstockback.service.RoleService;
import com.lloufa.gestionstockback.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UtilisateurService utilisateurService;

    private final RoleService roleService;

    @Autowired
    public ApplicationUserDetailsService(UtilisateurService utilisateurService, RoleService roleService) {
        this.utilisateurService = utilisateurService;
        this.roleService = roleService;
    }

    @Override
    public ExtendedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateurDto = this.utilisateurService.findByEmail(email);
        List<RoleDto> roleDtoList = this.roleService.findAllByUtilisateur(utilisateurDto);

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleDtoList)) roleDtoList.forEach(role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getNom())));

        return new ExtendedUser(utilisateurDto.getEmail(), utilisateurDto.getMotDePasse(), simpleGrantedAuthorities, utilisateurDto.getEntrepriseDto().getId());
    }

}
