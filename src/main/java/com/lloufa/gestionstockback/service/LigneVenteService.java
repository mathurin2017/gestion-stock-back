package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.LigneVenteDto;

import java.util.List;

public interface LigneVenteService {

    List<LigneVenteDto> findAllLigneVenteByArticleId(Integer idArticle);

}
