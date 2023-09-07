package com.lloufa.gestionstockback.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.FournisseurDto;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.service.FournisseurService;
import com.lloufa.gestionstockback.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurDto> {

    private final PhotoService photoService;
    private final FournisseurService fournisseurService;

    @Autowired
    public SaveFournisseurPhoto(PhotoService photoService, FournisseurService fournisseurService) {
        this.photoService = photoService;
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        FournisseurDto fournisseurDto = this.fournisseurService.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo du fournisseur", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        fournisseurDto.setPhoto(urlPhoto);

        return this.fournisseurService.save(fournisseurDto);
    }
}
