package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.EntrepriseApi;
import com.lloufa.gestionstockback.dto.EntrepriseDto;
import com.lloufa.gestionstockback.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseRestController implements EntrepriseApi {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseRestController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    public ResponseEntity<EntrepriseDto> save(EntrepriseDto entrepriseDto) {
        return ResponseEntity.ok(this.entrepriseService.save(entrepriseDto));
    }

    public ResponseEntity<EntrepriseDto> update(EntrepriseDto entrepriseDto) {
        return ResponseEntity.ok(this.entrepriseService.save(entrepriseDto));
    }

    public ResponseEntity<EntrepriseDto> findById(Integer id) {
        return ResponseEntity.ok(this.entrepriseService.findById(id));
    }

    public ResponseEntity<List<EntrepriseDto>> findAll() {
        return ResponseEntity.ok(this.entrepriseService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
