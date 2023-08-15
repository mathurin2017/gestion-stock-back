package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.VenteApi;
import com.lloufa.gestionstockback.dto.VenteDto;
import com.lloufa.gestionstockback.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteRestController implements VenteApi {

    private final VenteService venteService;

    @Autowired
    public VenteRestController(VenteService venteService) {
        this.venteService = venteService;
    }

    public VenteDto save(VenteDto venteDto) {
        return this.venteService.save(venteDto);
    }

    public VenteDto update(VenteDto venteDto) {
        return this.venteService.save(venteDto);
    }

    public VenteDto findById(Integer id) {
        return this.venteService.findById(id);
    }

    public List<VenteDto> findAll() {
        return this.venteService.findAll();
    }

    public void delete(Integer id) {
        this.venteService.delete(id);
    }
}
