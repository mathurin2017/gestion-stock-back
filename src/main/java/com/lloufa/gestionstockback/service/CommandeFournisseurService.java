package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    CommandeFournisseurDto updateEtat(Integer id, EtatCommande etatCommande);

    CommandeFournisseurDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idClient);

    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

    CommandeFournisseurDto findById(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();

    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idFournisseur);

    void delete(Integer id);

    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

}
