package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CommandeClientApi;
import com.lloufa.gestionstockback.dto.CommandeClientDto;
import com.lloufa.gestionstockback.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private final CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        return this.commandeClientService.save(commandeClientDto);
    }

    public CommandeClientDto update(CommandeClientDto commandeClientDto) {
        return this.commandeClientService.save(commandeClientDto);
    }

    public CommandeClientDto findById(Integer id) {
        return this.commandeClientService.findById(id);
    }

    public CommandeClientDto findByCode(String code) {
        return this.commandeClientService.findByCode(code);
    }

    public List<CommandeClientDto> findAll() {
        return this.commandeClientService.findAll();
    }

    public void delete(Integer id) {
        this.commandeClientService.delete(id);
    }
}
