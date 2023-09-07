package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CommandeClientApi;
import com.lloufa.gestionstockback.dto.CommandeClientDto;
import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.model.EtatCommande;
import com.lloufa.gestionstockback.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private final CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto commandeClientDto) {
        return ResponseEntity.ok(this.commandeClientService.save(commandeClientDto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> update(CommandeClientDto commandeClientDto) {
        return ResponseEntity.ok(this.commandeClientService.save(commandeClientDto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateEtat(Integer id, EtatCommande etatCommande) {
        return ResponseEntity.ok(this.commandeClientService.updateEtat(id, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(this.commandeClientService.updateQuantite(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateClient(Integer id, Integer idClient) {
        return ResponseEntity.ok(this.commandeClientService.updateClient(id, idClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(this.commandeClientService.updateArticle(idCommande, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        return ResponseEntity.ok(this.commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return ResponseEntity.ok(this.commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(this.commandeClientService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCommandeClientDto>> findAllLigneCommandeClientByCommandeClientId(Integer idClient) {
        return ResponseEntity.ok(this.commandeClientService.findHistoriqueCommandeClient(idClient));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.commandeClientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CommandeClientDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(this.commandeClientService.deleteArticle(idCommande, idLigneCommande));
    }

}
