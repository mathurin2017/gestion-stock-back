package com.lloufa.gestionstockback.controller;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.controller.api.ClientApi;
import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ClientRestController implements ClientApi {

    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientDto> save(ClientDto clientDto) {
        return ResponseEntity.ok(this.clientService.save(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> savePhoto(Integer id, MultipartFile photo, String title) throws IOException, FlickrException {
        return ResponseEntity.ok(this.clientService.savePhoto(id, photo.getInputStream(), title));
    }

    @Override
    public ResponseEntity<ClientDto> update(ClientDto clientDto) {
        return ResponseEntity.ok(this.clientService.save(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> findById(Integer id) {
        return ResponseEntity.ok(this.clientService.findById(id));
    }

    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(this.clientService.findAll());
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
