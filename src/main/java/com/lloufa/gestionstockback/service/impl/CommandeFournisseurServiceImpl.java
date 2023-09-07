package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.*;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.mapping.CommandeFournisseurMapping;
import com.lloufa.gestionstockback.mapping.LigneCommandeFournisseurMapping;
import com.lloufa.gestionstockback.model.*;
import com.lloufa.gestionstockback.repository.CommandeFournisseurRepository;
import com.lloufa.gestionstockback.service.*;
import com.lloufa.gestionstockback.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final LigneCommandeFournisseurService ligneCommandeFournisseurService;
    private final FournisseurService fournisseurService;
    private final ArticleService articleService;
    private final MvtStkService mvtStkService;

    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurService ligneCommandeFournisseurService, FournisseurService fournisseurService, ArticleService articleService, MvtStkService mvtStkService) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurService = ligneCommandeFournisseurService;
        this.fournisseurService = fournisseurService;
        this.articleService = articleService;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);
        if (!errors.isEmpty()) {
            log.error("Command fournisseur is not valid {}", commandeFournisseurDto);
            throw new InvalidEntityException("La commande fournisseur n'est pas valide ", ErrorCode.COMMAND_FOURNISSEUR_NOT_VALID, errors);
        }

        if (null != commandeFournisseurDto.getId() && CommandeFournisseurMapping.isCommandeLivree(commandeFournisseurDto)) {
            throw new InvalidEntityException("Impossible de modifier la commande fournisseur lorsqu'elle est livrée", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
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
                this.ligneCommandeFournisseurService.save(LigneCommandeFournisseurMapping.fromEntity(lineCmdFrs));
            }
        }

        return CommandeFournisseurMapping.fromEntity(savedCmdFrn);
    }

    @Override
    public CommandeFournisseurDto updateEtat(Integer id, EtatCommande etatCommande) {
        if (null == id) {
            log.error("Command fournisseur ID is null");
            throw new InvalidEntityException("Impossible de modifier la commande fournisseur avec un ID null", ErrorCode.COMMAND_FOURNISSEUR_INVALID_OPERATION);
        }

        if (StringUtils.hasLength(String.valueOf(etatCommande))) {
            throw new InvalidEntityException("Impossible de modifier la commande fournisseur lorsqu'elle un état commande null", ErrorCode.COMMAND_FOURNISSEUR_INVALID_OPERATION);
        }

        CommandeFournisseurDto commandeFournisseurDto = this.checkEtatCommande(id);

        commandeFournisseurDto.setEtatCommande(etatCommande);
        CommandeFournisseur savedCmdFrn = this.commandeFournisseurRepository.save(CommandeFournisseurMapping.toEntity(commandeFournisseurDto));
        if (CommandeFournisseurMapping.isCommandeLivree(commandeFournisseurDto)) this.updateMvtStk(id);

        return CommandeFournisseurMapping.fromEntity(savedCmdFrn);
    }

    @Override
    public CommandeFournisseurDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        this.checkIdCommande(idCommande);

        this.checkIdLigneCommande(idLigneCommande);

        CommandeFournisseurDto commandeFournisseurDto = this.checkEtatCommande(idCommande);

        if (null == quantite || quantite.compareTo(BigDecimal.ZERO) == 0) {
            log.error("Ligne command fournisseur QUANTITE is null");
            throw new InvalidEntityException("Impossible de modifier la ligne commande fournisseur avec une QUANTITE null ou zero", ErrorCode.COMMAND_FOURNISSEUR_INVALID_OPERATION);
        }

        LigneCommandeFournisseurDto ligneCommandeFournisseurDto = this.ligneCommandeFournisseurService.findById(idLigneCommande);
        ligneCommandeFournisseurDto.setQuantite(quantite);
        this.ligneCommandeFournisseurService.save(ligneCommandeFournisseurDto);

        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
        this.checkIdCommande(idCommande);

        if (null == idFournisseur) {
            log.error("Fournisseur ID is null");
            throw new InvalidEntityException("Impossible de modifier le fournisseur avec un ID null", ErrorCode.COMMAND_FOURNISSEUR_INVALID_OPERATION);
        }

        CommandeFournisseurDto commandeFournisseurDto = this.checkEtatCommande(idCommande);

        FournisseurDto fournisseurDto = this.fournisseurService.findById(idFournisseur);

        commandeFournisseurDto.setFournisseurDto(fournisseurDto);
        CommandeFournisseur savedCmdFrn = this.commandeFournisseurRepository.save(CommandeFournisseurMapping.toEntity(commandeFournisseurDto));

        return CommandeFournisseurMapping.fromEntity(savedCmdFrn);
    }

    @Override
    public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        this.checkIdCommande(idCommande);

        this.checkIdLigneCommande(idLigneCommande);

        if (null == idArticle) {
            log.error("Article ID is null");
            throw new InvalidEntityException("Impossible de modifier l'article avec un ID null", ErrorCode.COMMAND_ARTICLE_INVALID_OPERATION);
        }

        CommandeFournisseurDto commandeFournisseurDto = this.checkEtatCommande(idCommande);

        ArticleDto articleDto = this.articleService.findById(idArticle);

        LigneCommandeFournisseurDto ligneCommandeFournisseurDto = this.ligneCommandeFournisseurService.findById(idLigneCommande);
        ligneCommandeFournisseurDto.setArticleDto(articleDto);
        this.ligneCommandeFournisseurService.save(ligneCommandeFournisseurDto);

        return commandeFournisseurDto;
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (null == id) {
            log.error("Command fournisseur ID is null");
            return null;
        }

        return this.commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande fournisseur avec l'ID " + id + " n'a été trouvée dans la BDD", ErrorCode.COMMAND_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (null == code) {
            log.error("Command fournisseur CODE is null");
            return null;
        }

        return this.commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande fournisseur avec le CODE " + code + " n'a été trouvée dans la BDD", ErrorCode.COMMAND_FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        List<CommandeFournisseur> commandeFournisseurList = this.commandeFournisseurRepository.findAll();
        log.error("Number command fournisseur in BDD is {}", commandeFournisseurList.size());
        return commandeFournisseurList.stream().map(CommandeFournisseurMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idFournisseur) {
        return this.ligneCommandeFournisseurService.findAllLigneCommandeFournisseurByCommandeFournisseurId(idFournisseur);
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);

        Optional.ofNullable(this.ligneCommandeFournisseurService.findAllLigneCommandeFournisseurByCommandeFournisseurId(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer une commande fournisseur qui a déjà des lignes de commandes fournisseurs", ErrorCode.COMMAND_FOURNISSEUR_ALREADY_IN_USE));

        this.commandeFournisseurRepository.deleteById(id);
    }

    @Override
    public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        this.checkIdCommande(idCommande);

        this.checkIdLigneCommande(idLigneCommande);

        CommandeFournisseurDto commandeFournisseurDto = this.checkEtatCommande(idCommande);

        this.ligneCommandeFournisseurService.findById(idLigneCommande);
        this.ligneCommandeFournisseurService.delete(idLigneCommande);

        return commandeFournisseurDto;
    }

    private void checkIdCommande(Integer idCommande) {
        if (null == idCommande) {
            log.error("Command fournisseur ID is null");
            throw new InvalidEntityException("Impossible de modifier la commande fournisseur avec un ID null", ErrorCode.COMMAND_FOURNISSEUR_INVALID_OPERATION);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (null == idLigneCommande) {
            log.error("Ligne command fournisseur ID is null");
            throw new InvalidEntityException("Impossible de modifier la ligne commande fournisseur avec un ID null", ErrorCode.COMMAND_FOURNISSEUR_INVALID_OPERATION);
        }
    }

    private CommandeFournisseurDto checkEtatCommande(Integer idCommande) {
        CommandeFournisseurDto commandeFournisseurDto = findById(idCommande);
        if (null != commandeFournisseurDto.getId() && CommandeFournisseurMapping.isCommandeLivree(commandeFournisseurDto)) {
            throw new InvalidEntityException("Impossible de modifier la commande client lorsqu'elle est livrée", ErrorCode.COMMAND_FOURNISSEUR_INVALID_OPERATION);
        }

        return commandeFournisseurDto;
    }

    private void updateMvtStk(Integer idCommande) {
        List<LigneCommandeFournisseurDto> ligneCommandeFournisseurList = this.ligneCommandeFournisseurService.findAllLigneCommandeFournisseurByCommandeFournisseurId(idCommande);
        ligneCommandeFournisseurList.forEach(lgCmClt -> {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .articleDto(lgCmClt.getArticleDto())
                    .dateMvt(Instant.now())
                    .typeMvtStk(TypeMvtStk.ENTREE)
                    .sourceMvtStk(SourceMvtStk.COMMANDE_FOURNISSEUR)
                    .quantite(lgCmClt.getQuantite())
                    .idEntreprise(lgCmClt.getIdEntreprise())
                    .build();
            this.mvtStkService.entreeStock(mvtStkDto);
        });
    }

}
