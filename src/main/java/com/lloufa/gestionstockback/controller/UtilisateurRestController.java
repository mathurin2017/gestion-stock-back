package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.UtilisateurApi;
import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurRestController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurRestController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return this.utilisateurService.save(utilisateurDto);
    }

    public UtilisateurDto update(UtilisateurDto utilisateurDto) {
        return this.utilisateurService.save(utilisateurDto);
    }

    public UtilisateurDto findById(Integer id) {
        return this.utilisateurService.findById(id);
    }

    public List<UtilisateurDto> findAll() {
        return this.utilisateurService.findAll();
    }

    public void delete(Integer id) {
        this.utilisateurService.delete(id);
    }
}
