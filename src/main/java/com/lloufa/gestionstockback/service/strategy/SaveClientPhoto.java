package com.lloufa.gestionstockback.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.service.ClientService;
import com.lloufa.gestionstockback.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto> {

    private final PhotoService photoService;
    private final ClientService clientService;

    @Autowired
    public SaveClientPhoto(PhotoService photoService, ClientService clientService) {
        this.photoService = photoService;
        this.clientService = clientService;
    }

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        ClientDto clientDto = this.clientService.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo du client", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        clientDto.setPhoto(urlPhoto);

        return this.clientService.save(clientDto);
    }
}
