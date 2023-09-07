package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;

import java.util.List;

public interface LigneCommandeClientService {

    LigneCommandeClientDto save(LigneCommandeClientDto ligneCommandeClientDto);

    LigneCommandeClientDto findById(Integer id);

    List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(Integer idClient);

    List<LigneCommandeClientDto> findAllLigneCommandeClientByArticleId(Integer idArticle);

    void delete(Integer id);

}
