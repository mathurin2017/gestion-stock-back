package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.UtilisateurMapping;
import com.lloufa.gestionstockback.model.Utilisateur;
import com.lloufa.gestionstockback.repository.UtilisateurRepository;
import com.lloufa.gestionstockback.service.UtilisateurService;
import com.lloufa.gestionstockback.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide ", ErrorCode.UTILISATEUR_NOT_VALID, errors);
        }
        Utilisateur utilisateur = this.utilisateurRepository.save(UtilisateurMapping.toEntity(utilisateurDto));

        return UtilisateurMapping.fromEntity(utilisateur);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (null == id) {
            log.error("User ID is null");
            return null;
        }

        return this.utilisateurRepository.findById(id)
                .map(UtilisateurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec ID " + id + " n'a été trouvé dans la BDD", ErrorCode.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        if (null == email) {
            log.error("User EMAIL is null");
            return null;
        }

        return this.utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec EMAIL " + email + " n'a été trouvé dans la BDD", ErrorCode.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        List<Utilisateur> utilisateurList = this.utilisateurRepository.findAll();
        log.error("Number utilisateur in BDD is {}", utilisateurList.size());
        return utilisateurList.stream().map(UtilisateurMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        UtilisateurDto utilisateurDto = findById(id);
        if (null != utilisateurDto) this.utilisateurRepository.delete(UtilisateurMapping.toEntity(utilisateurDto));
    }

}
