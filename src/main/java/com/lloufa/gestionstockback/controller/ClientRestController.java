package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.ClientApi;
import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestController implements ClientApi {

    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    public ResponseEntity<ClientDto> save(ClientDto clientDto) {
        return ResponseEntity.ok(this.clientService.save(clientDto));
    }

    public ResponseEntity<ClientDto> update(ClientDto clientDto) {
        return ResponseEntity.ok(this.clientService.save(clientDto));
    }

    public ResponseEntity<ClientDto> findById(Integer id) {
        return ResponseEntity.ok(this.clientService.findById(id));
    }

    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(this.clientService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
