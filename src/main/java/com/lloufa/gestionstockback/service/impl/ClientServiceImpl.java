package com.lloufa.gestionstockback.service.impl;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.mapping.ClientMapping;
import com.lloufa.gestionstockback.model.Client;
import com.lloufa.gestionstockback.repository.ClientRepository;
import com.lloufa.gestionstockback.repository.CommandeClientRepository;
import com.lloufa.gestionstockback.service.ClientService;
import com.lloufa.gestionstockback.service.CommandeClientService;
import com.lloufa.gestionstockback.service.PhotoService;
import com.lloufa.gestionstockback.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PhotoService photoService;
    private final CommandeClientRepository commandeClientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, PhotoService photoService, CommandeClientRepository commandeClientRepository) {
        this.clientRepository = clientRepository;
        this.photoService = photoService;
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", clientDto);
            throw new InvalidEntityException("Le client n'est pas valide ", ErrorCode.CLIENT_NOT_VALID, errors);
        }
        Client client = this.clientRepository.save(ClientMapping.toEntity(clientDto));

        return ClientMapping.fromEntity(client);
    }

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        ClientDto clientDto = this.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo du client", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        clientDto.setPhoto(urlPhoto);

        return this.save(clientDto);
    }

    @Override
    public ClientDto findById(Integer id) {
        if (null == id) {
            log.error("Category ID is null");
            return null;
        }

        return this.clientRepository.findById(id)
                .map(ClientMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun client avec ID " + id + " n'a été trouvé dans la BDD", ErrorCode.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        List<Client> clientList = this.clientRepository.findAll();
        log.error("Number client in BDD is {}", clientList.size());
        return clientList.stream().map(ClientMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);

        Optional.ofNullable(this.commandeClientRepository.findAllByClientId(id))
                .orElseThrow(() -> new InvalidOperationException("Impossible de supprimer un client qui a déjà des commandes clients", ErrorCode.CLIENT_ALREADY_IN_USE));

        this.clientRepository.deleteById(id);
    }

}
