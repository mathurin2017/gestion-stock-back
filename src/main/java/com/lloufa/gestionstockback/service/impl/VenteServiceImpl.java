package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.LigneVenteDto;
import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.dto.VenteDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.mapping.LigneVenteMapping;
import com.lloufa.gestionstockback.mapping.VenteMapping;
import com.lloufa.gestionstockback.model.LigneVente;
import com.lloufa.gestionstockback.model.SourceMvtStk;
import com.lloufa.gestionstockback.model.TypeMvtStk;
import com.lloufa.gestionstockback.model.Vente;
import com.lloufa.gestionstockback.repository.LigneVenteRepository;
import com.lloufa.gestionstockback.repository.VenteRepository;
import com.lloufa.gestionstockback.service.ArticleService;
import com.lloufa.gestionstockback.service.MvtStkService;
import com.lloufa.gestionstockback.service.VenteService;
import com.lloufa.gestionstockback.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;
    private final ArticleService articleService;
    private final LigneVenteRepository ligneVenteRepository;
    private final MvtStkService mvtStkService;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, ArticleService articleService, LigneVenteRepository ligneVenteRepository, MvtStkService mvtStkService) {
        this.venteRepository = venteRepository;
        this.articleService = articleService;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public VenteDto save(VenteDto venteDto) {
        List<String> errors = VenteValidator.validate(venteDto);
        if (!errors.isEmpty()) {
            log.error("Vente is not valid {}", venteDto);
            throw new InvalidEntityException("La vente n'est pas valide ", ErrorCode.VENTE_NOT_VALID, errors);
        }

        List<LigneVenteDto> ligneVenteDtoList = venteDto.getLigneVenteDtos();
        for (LigneVenteDto ligneVenteDto : ligneVenteDtoList) {
            this.articleService.findById(ligneVenteDto.getVenteDto().getId());
        }

        Vente savedVente = this.venteRepository.save(VenteMapping.toEntity(venteDto));

        for (LigneVenteDto ligneVenteDto : ligneVenteDtoList) {
            LigneVente ligneVente = LigneVenteMapping.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
            this.updateMvtStk(ligneVenteDto);
        }
        return VenteMapping.fromEntity(savedVente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if (null == id) {
            log.error("Vente ID is null");
            return null;
        }
        return this.venteRepository.findById(id)
                .map(VenteMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune vente avec l'ID " + id + " n'a été trouvée dans la BDD", ErrorCode.VENTE_NOT_FOUND));
    }

    @Override
    public VenteDto findByCode(String code) {
        if (null == code) {
            log.error("Vente CODE is null");
            return null;
        }
        return this.venteRepository.findVenteByCode(code)
                .map(VenteMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune vente avec le CODE " + code + " n'a été trouvée dans la BDD", ErrorCode.VENTE_NOT_FOUND));
    }

    @Override
    public List<VenteDto> findAll() {
        List<Vente> venteList = this.venteRepository.findAll();
        log.error("Number vente in BDD is {}", venteList.size());
        return venteList.stream().map(VenteMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);

        Optional.ofNullable(this.ligneVenteRepository.findAllByVenteId(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer une vente qui a déjà des lignes de ventes", ErrorCode.VENTE_ALREADY_IN_USE));

        this.venteRepository.deleteById(id);
    }

    private void updateMvtStk(LigneVenteDto ligneVente) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .articleDto(ligneVente.getArticleDto())
                .dateMvt(Instant.now())
                .typeMvtStk(TypeMvtStk.SORTIE)
                .sourceMvtStk(SourceMvtStk.VENTE)
                .quantite(ligneVente.getQuantite())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
        this.mvtStkService.entreeStock(mvtStkDto);
    }

}
