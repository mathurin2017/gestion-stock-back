package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;

import java.util.List;

public interface LigneCommandeFournisseurService {

    LigneCommandeFournisseurDto save(LigneCommandeFournisseurDto ligneCommandeFournisseurDto);

    LigneCommandeFournisseurDto findById(Integer id);

    List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByCommandeFournisseurId(Integer idFournisseur);

    List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByArticleId(Integer idArticle);

    void delete(Integer id);

}
