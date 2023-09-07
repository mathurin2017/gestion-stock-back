package com.lloufa.gestionstockback.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.service.PhotoService;
import com.lloufa.gestionstockback.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {

    private final PhotoService photoService;
    private final UtilisateurService utilisateurService;

    @Autowired
    public SaveUtilisateurPhoto(PhotoService photoService, UtilisateurService utilisateurService) {
        this.photoService = photoService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        UtilisateurDto utilisateurDto = this.utilisateurService.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'utilisateur", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        utilisateurDto.setPhoto(urlPhoto);

        return this.utilisateurService.save(utilisateurDto);
    }
}
