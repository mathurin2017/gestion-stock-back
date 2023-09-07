package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.*;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.mapping.CommandeClientMapping;
import com.lloufa.gestionstockback.mapping.LigneCommandeClientMapping;
import com.lloufa.gestionstockback.model.*;
import com.lloufa.gestionstockback.repository.CommandeClientRepository;
import com.lloufa.gestionstockback.service.*;
import com.lloufa.gestionstockback.validator.CommandeClientValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;
    private final LigneCommandeClientService ligneCommandeClientService;
    private final ClientService clientService;
    private final ArticleService articleService;
    private final MvtStkService mvtStkService;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, LigneCommandeClientService ligneCommandeClientService, ClientService clientService, ArticleService articleService, MvtStkService mvtStkService) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientService = ligneCommandeClientService;
        this.clientService = clientService;
        this.articleService = articleService;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()) {
            log.error("Command client is not valid {}", commandeClientDto);
            throw new InvalidEntityException("La commande client n'est pas valide ", ErrorCode.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        if (null != commandeClientDto.getId() && CommandeClientMapping.isCommandeLivree(commandeClientDto)) {
            throw new InvalidEntityException("Impossible de modifier la commande client lorsqu'elle est livrée", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }

        this.clientService.findById(commandeClientDto.getClientDto().getId());

        List<LigneCommandeClientDto> ligneCommandeClientDtos = commandeClientDto.getLigneCommandeClientDtos();
        if (null != ligneCommandeClientDtos) {
            for (LigneCommandeClientDto lineCmdClt : ligneCommandeClientDtos) {
                this.articleService.findById(lineCmdClt.getArticleDto().getId());
            }
        }

        CommandeClient savedCmdClt = this.commandeClientRepository.save(CommandeClientMapping.toEntity(commandeClientDto));

        if (null != ligneCommandeClientDtos) {
            for (LigneCommandeClientDto lineCmdCltDto : ligneCommandeClientDtos) {
                LigneCommandeClient lineCmdClt = LigneCommandeClientMapping.toEntity(lineCmdCltDto);
                lineCmdClt.setCommandeClient(savedCmdClt);
                this.ligneCommandeClientService.save(LigneCommandeClientMapping.fromEntity(lineCmdClt));
            }
        }

        return CommandeClientMapping.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto updateEtat(Integer id, EtatCommande etatCommande) {
        if (null == id) {
            log.error("Command client ID is null");
            throw new InvalidEntityException("Impossible de modifier la commande client avec un ID null", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }

        if (StringUtils.hasLength(String.valueOf(etatCommande))) {
            throw new InvalidEntityException("Impossible de modifier la commande client lorsqu'elle un état commande null", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }

        CommandeClientDto commandeClientDto = this.checkEtatCommande(id);

        commandeClientDto.setEtatCommande(etatCommande);
        CommandeClient savedCmdClt = this.commandeClientRepository.save(CommandeClientMapping.toEntity(commandeClientDto));
        if (CommandeClientMapping.isCommandeLivree(commandeClientDto)) this.updateMvtStk(id);

        return CommandeClientMapping.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto updateQuantite(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        this.checkIdCommande(idCommande);

        this.checkIdLigneCommande(idLigneCommande);

        CommandeClientDto commandeClientDto = this.checkEtatCommande(idCommande);

        if (null == quantite || quantite.compareTo(BigDecimal.ZERO) == 0) {
            log.error("Ligne command client QUANTITE is null");
            throw new InvalidEntityException("Impossible de modifier la ligne commande client avec une QUANTITE null ou zero", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }

        LigneCommandeClientDto ligneCommandeClientDto = this.ligneCommandeClientService.findById(idLigneCommande);
        ligneCommandeClientDto.setQuantite(quantite);
        this.ligneCommandeClientService.save(ligneCommandeClientDto);

        return commandeClientDto;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        this.checkIdCommande(idCommande);

        if (null == idClient) {
            log.error("Client ID is null");
            throw new InvalidEntityException("Impossible de modifier le client avec un ID null", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }

        CommandeClientDto commandeClientDto = this.checkEtatCommande(idCommande);

        ClientDto clientDto = this.clientService.findById(idClient);

        commandeClientDto.setClientDto(clientDto);
        CommandeClient savedCmdClt = this.commandeClientRepository.save(CommandeClientMapping.toEntity(commandeClientDto));

        return CommandeClientMapping.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        this.checkIdCommande(idCommande);

        this.checkIdLigneCommande(idLigneCommande);

        if (null == idArticle) {
            log.error("Article ID is null");
            throw new InvalidEntityException("Impossible de modifier l'article avec un ID null", ErrorCode.COMMAND_ARTICLE_INVALID_OPERATION);
        }

        CommandeClientDto commandeClientDto = this.checkEtatCommande(idCommande);

        ArticleDto articleDto = this.articleService.findById(idArticle);

        LigneCommandeClientDto ligneCommandeClientDto = this.ligneCommandeClientService.findById(idLigneCommande);
        ligneCommandeClientDto.setArticleDto(articleDto);
        this.ligneCommandeClientService.save(ligneCommandeClientDto);

        return commandeClientDto;
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (null == id) {
            log.error("Command client ID is null");
            return null;
        }

        return this.commandeClientRepository.findById(id)
                .map(CommandeClientMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client avec l'ID " + id + " n'a été trouvée dans la BDD", ErrorCode.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (null == code) {
            log.error("Command client CODE is null");
            return null;
        }

        return this.commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune commande client avec le CODE " + code + " n'a été trouvée dans la BDD", ErrorCode.COMMANDE_CLIENT_NOT_FOUND));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        List<CommandeClient> commandeClientList = this.commandeClientRepository.findAll();
        log.error("Number command client in BDD is {}", commandeClientList.size());
        return commandeClientList.stream().map(CommandeClientMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idClient) {
        return this.ligneCommandeClientService.findAllLigneCommandeClientByCommandeClientId(idClient);
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);

        Optional.ofNullable(this.ligneCommandeClientService.findAllLigneCommandeClientByCommandeClientId(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer une commande client qui a déjà des lignes de commandes clients", ErrorCode.COMMAND_CLIENT_ALREADY_IN_USE));

        this.commandeClientRepository.deleteById(id);
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        this.checkIdCommande(idCommande);

        this.checkIdLigneCommande(idLigneCommande);

        CommandeClientDto commandeClientDto = this.checkEtatCommande(idCommande);

        this.ligneCommandeClientService.findById(idLigneCommande);
        this.ligneCommandeClientService.delete(idLigneCommande);

        return commandeClientDto;
    }

    private void checkIdCommande(Integer idCommande) {
        if (null == idCommande) {
            log.error("Command client ID is null");
            throw new InvalidEntityException("Impossible de modifier la commande client avec un ID null", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }
    }

    private void checkIdLigneCommande(Integer idLigneCommande) {
        if (null == idLigneCommande) {
            log.error("Ligne command client ID is null");
            throw new InvalidEntityException("Impossible de modifier la ligne commande client avec un ID null", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }
    }

    private CommandeClientDto checkEtatCommande(Integer idCommande) {
        CommandeClientDto commandeClientDto = findById(idCommande);
        if (null != commandeClientDto.getId() && CommandeClientMapping.isCommandeLivree(commandeClientDto)) {
            throw new InvalidEntityException("Impossible de modifier la commande client lorsqu'elle est livrée", ErrorCode.COMMAND_CLIENT_INVALID_OPERATION);
        }

        return commandeClientDto;
    }

    private void updateMvtStk(Integer idCommande) {
        List<LigneCommandeClientDto> ligneCommandeClientList = this.ligneCommandeClientService.findAllLigneCommandeClientByCommandeClientId(idCommande);
        ligneCommandeClientList.forEach(lgCmClt -> {
            MvtStkDto mvtStkDto = MvtStkDto.builder()
                    .articleDto(lgCmClt.getArticleDto())
                    .dateMvt(Instant.now())
                    .typeMvtStk(TypeMvtStk.SORTIE)
                    .sourceMvtStk(SourceMvtStk.COMMANDE_CLIENT)
                    .quantite(lgCmClt.getQuantite())
                    .idEntreprise(lgCmClt.getIdEntreprise())
                    .build();
            this.mvtStkService.sortieStock(mvtStkDto);
        });
    }

}
