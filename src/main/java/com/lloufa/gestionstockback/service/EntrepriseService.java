package com.lloufa.gestionstockback.service;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.dto.EntrepriseDto;

import java.io.InputStream;
import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto entrepriseDto);

    EntrepriseDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException;

    EntrepriseDto findById(Integer id);

    List<EntrepriseDto> findAll();

    void delete(Integer id);

}
