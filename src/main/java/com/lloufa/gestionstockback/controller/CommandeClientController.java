package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CommandeClientApi;
import com.lloufa.gestionstockback.dto.CommandeClientDto;
import com.lloufa.gestionstockback.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private final CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    public ResponseEntity<CommandeClientDto> save(CommandeClientDto commandeClientDto) {
        return ResponseEntity.ok(this.commandeClientService.save(commandeClientDto));
    }

    public ResponseEntity<CommandeClientDto> update(CommandeClientDto commandeClientDto) {
        return ResponseEntity.ok(this.commandeClientService.save(commandeClientDto));
    }

    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(this.commandeClientService.findById(id));
    }

    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(this.commandeClientService.findByCode(code));
    }

    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(this.commandeClientService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.commandeClientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
