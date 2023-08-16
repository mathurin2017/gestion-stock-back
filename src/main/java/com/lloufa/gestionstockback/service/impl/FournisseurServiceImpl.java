package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.FournisseurDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.FournisseurMapping;
import com.lloufa.gestionstockback.model.Fournisseur;
import com.lloufa.gestionstockback.repository.FournisseurRepository;
import com.lloufa.gestionstockback.service.FournisseurService;
import com.lloufa.gestionstockback.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()) {
            log.error("Fournisseur is not valid {}", fournisseurDto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide ", ErrorCode.FOURNISSEUR_NOT_VALID, errors);
        }
        Fournisseur fournisseur = this.fournisseurRepository.save(FournisseurMapping.toEntity(fournisseurDto));

        return FournisseurMapping.fromEntity(fournisseur);
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (null == id) {
            log.error("Fournisseur ID is null");
            return null;
        }

        return this.fournisseurRepository.findById(id)
                .map(FournisseurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun fournisseur avec ID " + id + " n'a été trouvé dans la BDD", ErrorCode.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<FournisseurDto> findAll() {
        List<Fournisseur> fournisseurList = this.fournisseurRepository.findAll();
        log.error("Number fournisseur in BDD is {}", fournisseurList.size());
        return fournisseurList.stream().map(FournisseurMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        FournisseurDto fournisseurDto = findById(id);
        if (null != fournisseurDto) this.fournisseurRepository.delete(FournisseurMapping.toEntity(fournisseurDto));
    }

}
