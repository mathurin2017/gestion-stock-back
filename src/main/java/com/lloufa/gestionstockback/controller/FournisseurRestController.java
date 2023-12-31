package com.lloufa.gestionstockback.controller;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.controller.api.FournisseurApi;
import com.lloufa.gestionstockback.dto.FournisseurDto;
import com.lloufa.gestionstockback.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class FournisseurRestController implements FournisseurApi {

    private final FournisseurService fournisseurService;

    @Autowired
    public FournisseurRestController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ResponseEntity<FournisseurDto> save(FournisseurDto fournisseurDto) {
        return ResponseEntity.ok(this.fournisseurService.save(fournisseurDto));
    }

    @Override
    public ResponseEntity<FournisseurDto> savePhoto(Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return ResponseEntity.ok(this.fournisseurService.savePhoto(id, photo.getInputStream(), title));
    }

    @Override
    public ResponseEntity<FournisseurDto> update(FournisseurDto fournisseurDto) {
        return ResponseEntity.ok(this.fournisseurService.save(fournisseurDto));
    }

    @Override
    public ResponseEntity<FournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(this.fournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<List<FournisseurDto>> findAll() {
        return ResponseEntity.ok(this.fournisseurService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.fournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
