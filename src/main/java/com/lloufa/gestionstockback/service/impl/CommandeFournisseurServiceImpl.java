package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.CommandeFournisseurMapping;
import com.lloufa.gestionstockback.mapping.LigneCommandeFournisseurMapping;
import com.lloufa.gestionstockback.model.CommandeFournisseur;
import com.lloufa.gestionstockback.model.LigneCommandeFournisseur;
import com.lloufa.gestionstockback.repository.CommandeFournisseurRepository;
import com.lloufa.gestionstockback.repository.LigneCommandeFournisseurRepository;
import com.lloufa.gestionstockback.service.ArticleService;
import com.lloufa.gestionstockback.service.CommandeFournisseurService;
import com.lloufa.gestionstockback.service.FournisseurService;
import com.lloufa.gestionstockback.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final FournisseurService fournisseurService;
    private final ArticleService articleService;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, FournisseurService fournisseurService, ArticleService articleService) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurService = fournisseurService;
        this.articleService = articleService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);
        if (!errors.isEmpty()) {
            log.error("Command fournisseur is not valid {}", commandeFournisseurDto);
            throw new InvalidEntityException("La commande fournisseur n'est pas valide ", ErrorCode.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        this.fournisseurService.findById(commandeFournisseurDto.getFournisseurDto().getId());

        List<LigneCommandeFournisseurDto> ligneCommandeFournisseurDtos = commandeFournisseurDto.getLigneCommandeFournisseurDtos();
        if (null != ligneCommandeFournisseurDtos) {
            for (LigneCommandeFournisseurDto lineCmdFrs : ligneCommandeFournisseurDtos) {
                this.articleService.findById(lineCmdFrs.getArticleDto().getId());
            }
        }

        CommandeFournisseur savedCmdFrn = this.commandeFournisseurRepository.save(CommandeFournisseurMapping.toEntity(commandeFournisseurDto));

        if (null != ligneCommandeFournisseurDtos) {
            for (LigneCommandeFournisseurDto lineCmdFrsDto : ligneCommandeFournisseurDtos) {
                LigneCommandeFournisseur lineCmdFrs = LigneCommandeFournisseurMapping.toEntity(lineCmdFrsDto);
                lineCmdFrs.setCommandeFournisseur(savedCmdFrn);
                this.ligneCommandeFournisseurRepository.save(lineCmdFrs);
            }
        }

        return CommandeFournisseurMapping.fromEntity(savedCmdFrn);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (null == id) {
            log.error("Command fournisseur ID is null");
            return null;
        }

        return this.commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande fournisseur avec l'ID " + id + " n'a été trouvée dans la BDD", ErrorCode.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (null == code) {
            log.error("Command fournisseur CODE is null");
            return null;
        }

        return this.commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande fournisseur avec le CODE " + code + " n'a été trouvée dans la BDD", ErrorCode.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        List<CommandeFournisseur> commandeFournisseurList = this.commandeFournisseurRepository.findAll();
        log.error("Number command fournisseur in BDD is {}", commandeFournisseurList.size());
        return commandeFournisseurList.stream().map(CommandeFournisseurMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        CommandeFournisseurDto commandeFournisseurDto = findById(id);
        if (null != commandeFournisseurDto) this.commandeFournisseurRepository.delete(CommandeFournisseurMapping.toEntity(commandeFournisseurDto));
    }

}
