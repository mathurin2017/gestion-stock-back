package com.lloufa.gestionstockback.controller;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.controller.api.EntrepriseApi;
import com.lloufa.gestionstockback.dto.EntrepriseDto;
import com.lloufa.gestionstockback.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class EntrepriseRestController implements EntrepriseApi {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseRestController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public ResponseEntity<EntrepriseDto> save(EntrepriseDto entrepriseDto) {
        return ResponseEntity.ok(this.entrepriseService.save(entrepriseDto));
    }

    @Override
    public ResponseEntity<EntrepriseDto> savePhoto(Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return ResponseEntity.ok(this.entrepriseService.savePhoto(id, photo.getInputStream(), title));
    }

    @Override
    public ResponseEntity<EntrepriseDto> update(EntrepriseDto entrepriseDto) {
        return ResponseEntity.ok(this.entrepriseService.save(entrepriseDto));
    }

    @Override
    public ResponseEntity<EntrepriseDto> findById(Integer id) {
        return ResponseEntity.ok(this.entrepriseService.findById(id));
    }

    @Override
    public ResponseEntity<List<EntrepriseDto>> findAll() {
        return ResponseEntity.ok(this.entrepriseService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.entrepriseService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
