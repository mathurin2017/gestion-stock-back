package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.LigneCommandeFournisseurMapping;
import com.lloufa.gestionstockback.model.LigneCommandeFournisseur;
import com.lloufa.gestionstockback.repository.LigneCommandeFournisseurRepository;
import com.lloufa.gestionstockback.service.LigneCommandeFournisseurService;
import com.lloufa.gestionstockback.validator.LigneCommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LigneCommandeFournisseurServiceImpl implements LigneCommandeFournisseurService {

    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public LigneCommandeFournisseurServiceImpl(LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }


    @Override
    public LigneCommandeFournisseurDto save(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        List<String> errors = LigneCommandeFournisseurValidator.validate(ligneCommandeFournisseurDto);
        if (!errors.isEmpty()) {
            log.error("Line command fournisseur is not valid {}", ligneCommandeFournisseurDto);
            throw new InvalidEntityException("La ligne commande fournisseur n'est pas valide ", ErrorCode.LINE_COMMAND_FOURNISSEUR_NOT_VALID, errors);
        }
        LigneCommandeFournisseur ligneCommandeFournisseur = this.ligneCommandeFournisseurRepository.save(LigneCommandeFournisseurMapping.toEntity(ligneCommandeFournisseurDto));

        return LigneCommandeFournisseurMapping.fromEntity(ligneCommandeFournisseur);
    }

    @Override
    public LigneCommandeFournisseurDto findById(Integer id) {
        if (null == id) {
            log.error("Line command fournisseur ID is null");
            return null;
        }

        return this.ligneCommandeFournisseurRepository.findById(id)
                .map(LigneCommandeFournisseurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune ligne commande fournisseur avec l'ID " + id + " n'a été trouvée dans la BDD", ErrorCode.LINE_COMMAND_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByCommandeFournisseurId(Integer idFournisseur) {
        List<LigneCommandeFournisseur> ligneCommandeFournisseurList = this.ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idFournisseur);
        log.error("Number ligne command fournisseur by fournisseur in BDD is {}", ligneCommandeFournisseurList.size());
        return ligneCommandeFournisseurList.stream().map(LigneCommandeFournisseurMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByArticleId(Integer idArticle) {
        List<LigneCommandeFournisseur> ligneCommandeFournisseurList = this.ligneCommandeFournisseurRepository.findAllByArticleId(idArticle);
        log.error("Number ligne command fournisseur by article in BDD is {}", ligneCommandeFournisseurList.size());
        return ligneCommandeFournisseurList.stream().map(LigneCommandeFournisseurMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        this.ligneCommandeFournisseurRepository.deleteById(id);
    }

}
