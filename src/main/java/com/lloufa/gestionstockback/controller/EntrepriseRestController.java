package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.EntrepriseApi;
import com.lloufa.gestionstockback.dto.EntrepriseDto;
import com.lloufa.gestionstockback.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseRestController implements EntrepriseApi {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseRestController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        return this.entrepriseService.save(entrepriseDto);
    }

    public EntrepriseDto update(EntrepriseDto entrepriseDto) {
        return this.entrepriseService.save(entrepriseDto);
    }

    public EntrepriseDto findById(Integer id) {
        return this.entrepriseService.findById(id);
    }

    public List<EntrepriseDto> findAll() {
        return this.entrepriseService.findAll();
    }

    public void delete(Integer id) {
        this.entrepriseService.delete(id);
    }
}
