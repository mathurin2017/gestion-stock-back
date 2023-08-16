package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CommandeFournisseurApi;
import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import com.lloufa.gestionstockback.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private final CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(this.commandeFournisseurService.save(commandeFournisseurDto));
    }

    public ResponseEntity<CommandeFournisseurDto> update(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(this.commandeFournisseurService.save(commandeFournisseurDto));
    }

    public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(this.commandeFournisseurService.findById(id));
    }

    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(this.commandeFournisseurService.findByCode(code));
    }

    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(this.commandeFournisseurService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.commandeFournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
