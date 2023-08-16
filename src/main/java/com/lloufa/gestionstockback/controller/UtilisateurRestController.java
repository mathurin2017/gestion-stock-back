package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.UtilisateurApi;
import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurRestController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurRestController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public ResponseEntity<UtilisateurDto> save(UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(this.utilisateurService.save(utilisateurDto));
    }

    public ResponseEntity<UtilisateurDto> update(UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(this.utilisateurService.save(utilisateurDto));
    }

    public ResponseEntity<UtilisateurDto> findById(Integer id) {
        return ResponseEntity.ok(this.utilisateurService.findById(id));
    }

    public ResponseEntity<List<UtilisateurDto>> findAll() {
        return ResponseEntity.ok(this.utilisateurService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
