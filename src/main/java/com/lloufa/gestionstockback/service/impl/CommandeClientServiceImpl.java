package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.CommandeClientDto;
import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.CommandeClientMapping;
import com.lloufa.gestionstockback.mapping.LigneCommandeClientMapping;
import com.lloufa.gestionstockback.model.CommandeClient;
import com.lloufa.gestionstockback.model.LigneCommandeClient;
import com.lloufa.gestionstockback.repository.CommandeClientRepository;
import com.lloufa.gestionstockback.repository.LigneCommandeClientRepository;
import com.lloufa.gestionstockback.service.ArticleService;
import com.lloufa.gestionstockback.service.ClientService;
import com.lloufa.gestionstockback.service.CommandeClientService;
import com.lloufa.gestionstockback.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientRepository commandeClientRepository;
    private final LigneCommandeClientRepository ligneCommandeClientRepository;
    private final ClientService clientService;
    private final ArticleService articleService;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, LigneCommandeClientRepository ligneCommandeClientRepository, ClientService clientService, ArticleService articleService) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientService = clientService;
        this.articleService = articleService;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()) {
            log.error("Command client is not valid {}", commandeClientDto);
            throw new InvalidEntityException("La commande client n'est pas valide ", ErrorCode.COMMANDE_CLIENT_NOT_VALID, errors);
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
                this.ligneCommandeClientRepository.save(lineCmdClt);
            }
        }

        return CommandeClientMapping.fromEntity(savedCmdClt);
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
    public void delete(Integer id) {
        CommandeClientDto commandeClientDto = findById(id);
        if (null != commandeClientDto) this.commandeClientRepository.delete(CommandeClientMapping.toEntity(commandeClientDto));
    }

}
