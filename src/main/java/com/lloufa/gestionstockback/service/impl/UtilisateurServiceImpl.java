package com.lloufa.gestionstockback.service.impl;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ChangePasswordUserDto;
import com.lloufa.gestionstockback.dto.UtilisateurDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.mapping.UtilisateurMapping;
import com.lloufa.gestionstockback.model.Utilisateur;
import com.lloufa.gestionstockback.repository.UtilisateurRepository;
import com.lloufa.gestionstockback.service.PhotoService;
import com.lloufa.gestionstockback.service.UtilisateurService;
import com.lloufa.gestionstockback.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PhotoService photoService;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, PhotoService photoService) {
        this.utilisateurRepository = utilisateurRepository;
        this.photoService = photoService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()) {
            log.error("User is not valid {}", utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide ", ErrorCode.USER_NOT_VALID, errors);
        }
        Utilisateur savedUtilisateur = this.utilisateurRepository.save(UtilisateurMapping.toEntity(utilisateurDto));

        return UtilisateurMapping.fromEntity(savedUtilisateur);
    }

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        UtilisateurDto utilisateurDto = this.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'utilisateur", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        utilisateurDto.setPhoto(urlPhoto);

        return this.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (null == id) {
            log.error("User ID is null");
            return null;
        }

        return this.utilisateurRepository.findById(id)
                .map(UtilisateurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec ID " + id + " n'a été trouvé dans la BDD", ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        if (null == email) {
            log.error("User EMAIL is null");
            return null;
        }

        return this.utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec EMAIL " + email + " n'a été trouvé dans la BDD", ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public UtilisateurDto changePassword(ChangePasswordUserDto changePasswordUserDto) {
        this.validate(changePasswordUserDto);

        UtilisateurDto utilisateurDto = this.findById(changePasswordUserDto.getId());
        utilisateurDto.setMotDePasse(changePasswordUserDto.getMotDePasse());
        Utilisateur savedUtilisateur = this.utilisateurRepository.save(UtilisateurMapping.toEntity(utilisateurDto));

        return UtilisateurMapping.fromEntity(savedUtilisateur);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        List<Utilisateur> utilisateurList = this.utilisateurRepository.findAll();
        log.error("Number utilisateur in BDD is {}", utilisateurList.size());
        return utilisateurList.stream().map(UtilisateurMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        this.findById(id);
        this.utilisateurRepository.deleteById(id);
    }

    private void validate(ChangePasswordUserDto changePasswordUserDto) {
        if (null == changePasswordUserDto) {
            log.error("ChangePasswordUserDto is null");
            throw new InvalidOperationException("Impossible de modifier le mot de passe", ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

        if (null == changePasswordUserDto.getId()) {
            log.error("ChangePasswordUserDto ID is null");
            throw new InvalidOperationException("Impossible de modifier le mot de passe", ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

        if (!StringUtils.hasLength(changePasswordUserDto.getMotDePasse()) || !StringUtils.hasLength(changePasswordUserDto.getConfirmMotDePasse())) {
            log.error("ChangePasswordUserDto MotDePasse or ConfirmMotDePasse is null");
            throw new InvalidOperationException("Impossible de modifier le mot de passe", ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }

        if (changePasswordUserDto.getMotDePasse().equals(changePasswordUserDto.getConfirmMotDePasse())) {
            log.error("ChangePasswordUserDto MotDePasse and ConfirmMotDePasse is equals");
            throw new InvalidOperationException("Impossible de modifier le mot de passe", ErrorCode.USER_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
    }

}
