package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CommandeFournisseurApi;
import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import com.lloufa.gestionstockback.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private final CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        return this.commandeFournisseurService.save(commandeFournisseurDto);
    }

    public CommandeFournisseurDto update(CommandeFournisseurDto commandeFournisseurDto) {
        return this.commandeFournisseurService.save(commandeFournisseurDto);
    }

    public CommandeFournisseurDto findById(Integer id) {
        return this.commandeFournisseurService.findById(id);
    }

    public CommandeFournisseurDto findByCode(String code) {
        return this.commandeFournisseurService.findByCode(code);
    }

    public List<CommandeFournisseurDto> findAll() {
        return this.commandeFournisseurService.findAll();
    }

    public void delete(Integer id) {
        this.commandeFournisseurService.delete(id);
    }
}
