package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.LigneVenteDto;
import com.lloufa.gestionstockback.mapping.LigneVenteMapping;
import com.lloufa.gestionstockback.model.LigneVente;
import com.lloufa.gestionstockback.repository.LigneVenteRepository;
import com.lloufa.gestionstockback.service.LigneVenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LigneVenteServiceImpl implements LigneVenteService {

    private final LigneVenteRepository ligneVenteRepository;

    @Autowired
    public LigneVenteServiceImpl(LigneVenteRepository ligneVenteRepository) {
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public List<LigneVenteDto> findAllLigneVenteByArticleId(Integer idArticle) {
        List<LigneVente> ligneVenteDtoList = this.ligneVenteRepository.findAllByArticleId(idArticle);
        log.error("Number ligne vente by article in BDD is {}", ligneVenteDtoList.size());
        return ligneVenteDtoList.stream().map(LigneVenteMapping::fromEntity).collect(Collectors.toList());
    }

}
