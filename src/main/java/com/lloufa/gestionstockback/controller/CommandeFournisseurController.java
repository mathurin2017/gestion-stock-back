package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.CommandeFournisseurApi;
import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.model.EtatCommande;
import com.lloufa.gestionstockback.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private final CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(this.commandeFournisseurService.save(commandeFournisseurDto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> update(CommandeFournisseurDto commandeFournisseurDto) {
        return ResponseEntity.ok(this.commandeFournisseurService.save(commandeFournisseurDto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateEtat(Integer id, EtatCommande etatCommande) {
        return ResponseEntity.ok(this.commandeFournisseurService.updateEtat(id, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return ResponseEntity.ok(this.commandeFournisseurService.updateQuantite(idCommande, idLigneCommande, quantite));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateFournisseur(Integer id, Integer idFournisseur) {
        return ResponseEntity.ok(this.commandeFournisseurService.updateFournisseur(id, idFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return ResponseEntity.ok(this.commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer id) {
        return ResponseEntity.ok(this.commandeFournisseurService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String code) {
        return ResponseEntity.ok(this.commandeFournisseurService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(this.commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLigneCommandeFournisseurByCommandeFournisseurId(Integer idFournisseur) {
        return ResponseEntity.ok(this.commandeFournisseurService.findHistoriqueCommandeFournisseur(idFournisseur));
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        this.commandeFournisseurService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return ResponseEntity.ok(this.commandeFournisseurService.deleteArticle(idCommande, idLigneCommande));
    }

}
