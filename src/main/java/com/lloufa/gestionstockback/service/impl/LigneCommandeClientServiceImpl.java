package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.LigneCommandeClientMapping;
import com.lloufa.gestionstockback.model.LigneCommandeClient;
import com.lloufa.gestionstockback.repository.LigneCommandeClientRepository;
import com.lloufa.gestionstockback.service.LigneCommandeClientService;
import com.lloufa.gestionstockback.validator.LigneCommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LigneCommandeClientServiceImpl implements LigneCommandeClientService {

    private final LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public LigneCommandeClientServiceImpl(LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }


    @Override
    public LigneCommandeClientDto save(LigneCommandeClientDto ligneCommandeClientDto) {
        List<String> errors = LigneCommandeClientValidator.validate(ligneCommandeClientDto);
        if (!errors.isEmpty()) {
            log.error("Line command client is not valid {}", ligneCommandeClientDto);
            throw new InvalidEntityException("La ligne commande client n'est pas valide ", ErrorCode.LINE_COMMAND_CLIENT_NOT_VALID, errors);
        }
        LigneCommandeClient ligneCommandeClient = this.ligneCommandeClientRepository.save(LigneCommandeClientMapping.toEntity(ligneCommandeClientDto));

        return LigneCommandeClientMapping.fromEntity(ligneCommandeClient);
    }

    @Override
    public LigneCommandeClientDto findById(Integer id) {
        if (null == id) {
            log.error("Line command client ID is null");
            return null;
        }

        return this.ligneCommandeClientRepository.findById(id)
                .map(LigneCommandeClientMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune ligne commande client avec l'ID " + id + " n'a été trouvée dans la BDD", ErrorCode.LINE_COMMAND_CLIENT_NOT_FOUND));
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(Integer idClient) {
        List<LigneCommandeClient> ligneCommandeClientList = this.ligneCommandeClientRepository.findAllByCommandeClientId(idClient);
        log.error("Number ligne command client by client in BDD is {}", ligneCommandeClientList.size());
        return ligneCommandeClientList.stream().map(LigneCommandeClientMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findAllLigneCommandeClientByArticleId(Integer idArticle) {
        List<LigneCommandeClient> ligneCommandeClientList = this.ligneCommandeClientRepository.findAllByArticleId(idArticle);
        log.error("Number ligne command client by article in BDD is {}", ligneCommandeClientList.size());
        return ligneCommandeClientList.stream().map(LigneCommandeClientMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        this.ligneCommandeClientRepository.deleteById(id);
    }

}
