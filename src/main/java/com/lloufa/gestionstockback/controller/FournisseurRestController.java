package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.FournisseurApi;
import com.lloufa.gestionstockback.dto.FournisseurDto;
import com.lloufa.gestionstockback.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurRestController implements FournisseurApi {

    private final FournisseurService fournisseurService;

    @Autowired
    public FournisseurRestController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    public FournisseurDto save(FournisseurDto fournisseurDto) {
        return this.fournisseurService.save(fournisseurDto);
    }

    public FournisseurDto update(FournisseurDto fournisseurDto) {
        return this.fournisseurService.save(fournisseurDto);
    }

    public FournisseurDto findById(Integer id) {
        return this.fournisseurService.findById(id);
    }

    public List<FournisseurDto> findAll() {
        return this.fournisseurService.findAll();
    }

    public void delete(Integer id) {
        this.fournisseurService.delete(id);
    }
}
