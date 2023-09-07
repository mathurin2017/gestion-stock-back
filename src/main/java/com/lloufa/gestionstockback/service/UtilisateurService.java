package com.lloufa.gestionstockback.service;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ChangePasswordUserDto;
import com.lloufa.gestionstockback.dto.UtilisateurDto;

import java.io.InputStream;
import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException;

    UtilisateurDto findById(Integer id);

    UtilisateurDto findByEmail(String email);

    UtilisateurDto changePassword(ChangePasswordUserDto changePasswordUserDto);

    List<UtilisateurDto> findAll();

    void delete(Integer id);

}
