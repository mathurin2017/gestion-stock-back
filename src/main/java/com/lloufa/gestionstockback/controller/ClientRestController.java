package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.ClientApi;
import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestController implements ClientApi {

    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    public ClientDto save(ClientDto clientDto) {
        return this.clientService.save(clientDto);
    }

    public ClientDto update(ClientDto clientDto) {
        return this.clientService.save(clientDto);
    }

    public ClientDto findById(Integer id) {
        return this.clientService.findById(id);
    }

    public List<ClientDto> findAll() {
        return this.clientService.findAll();
    }

    public void delete(Integer id) {
        this.clientService.delete(id);
    }
}
