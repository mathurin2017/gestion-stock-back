package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.CommandeClientDto;
import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);

    CommandeClientDto updateEtat(Integer id, EtatCommande etatCommande);

    CommandeClientDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Integer idCommande, Integer idClient);

    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idClient);

    void delete(Integer id);

    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);

}
