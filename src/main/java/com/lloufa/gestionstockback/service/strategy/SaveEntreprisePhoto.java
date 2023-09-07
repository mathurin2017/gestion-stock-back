package com.lloufa.gestionstockback.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.EntrepriseDto;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.service.EntrepriseService;
import com.lloufa.gestionstockback.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto> {

    private final PhotoService photoService;
    private final EntrepriseService entrepriseService;

    @Autowired
    public SaveEntreprisePhoto(PhotoService photoService, EntrepriseService entrepriseService) {
        this.photoService = photoService;
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        EntrepriseDto entrepriseDto = this.entrepriseService.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'entreprise", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        entrepriseDto.setPhoto(urlPhoto);

        return this.entrepriseService.save(entrepriseDto);
    }
}