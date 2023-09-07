package com.lloufa.gestionstockback.controller;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.controller.api.UtilisateurApi;
import com.lloufa.gestionstockback.dto.ChangePasswordUserDto;
import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class UtilisateurRestController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurRestController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(this.utilisateurService.save(utilisateurDto));
    }

    @Override
    public ResponseEntity<UtilisateurDto> savePhoto(Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return ResponseEntity.ok(this.utilisateurService.savePhoto(id, photo.getInputStream(), title));
    }

    @Override
    public ResponseEntity<UtilisateurDto> update(UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(this.utilisateurService.save(utilisateurDto));
    }

    @Override
    public ResponseEntity<UtilisateurDto> updatePassword(ChangePasswordUserDto changePasswordUserDto) {
        return ResponseEntity.ok(this.utilisateurService.changePassword(changePasswordUserDto));
    }

    @Override
    public ResponseEntity<UtilisateurDto> findById(Integer id) {
        return ResponseEntity.ok(this.utilisateurService.findById(id));
    }

    @Override
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        return ResponseEntity.ok(this.utilisateurService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
