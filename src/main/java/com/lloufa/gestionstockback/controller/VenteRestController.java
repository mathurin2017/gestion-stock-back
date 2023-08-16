package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.VenteApi;
import com.lloufa.gestionstockback.dto.VenteDto;
import com.lloufa.gestionstockback.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteRestController implements VenteApi {

    private final VenteService venteService;

    @Autowired
    public VenteRestController(VenteService venteService) {
        this.venteService = venteService;
    }

    public ResponseEntity<VenteDto> save(VenteDto venteDto) {
        return ResponseEntity.ok(this.venteService.save(venteDto));
    }

    public ResponseEntity<VenteDto> update(VenteDto venteDto) {
        return ResponseEntity.ok(this.venteService.save(venteDto));
    }

    public ResponseEntity<VenteDto> findById(Integer id) {
        return ResponseEntity.ok(this.venteService.findById(id));
    }

    public ResponseEntity<List<VenteDto>> findAll() {
        return ResponseEntity.ok(this.venteService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.venteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
