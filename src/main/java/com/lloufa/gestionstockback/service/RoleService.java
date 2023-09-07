package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.RoleDto;
import com.lloufa.gestionstockback.dto.UtilisateurDto;

import java.util.List;

public interface RoleService {

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Integer id);

    RoleDto findByNom(String nom);

    List<RoleDto> findAll();

    List<RoleDto> findAllByUtilisateur(UtilisateurDto utilisateurDto);

}
