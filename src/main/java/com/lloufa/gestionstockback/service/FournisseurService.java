package com.lloufa.gestionstockback.service;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.dto.FournisseurDto;

import java.io.InputStream;
import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException;

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    void delete(Integer id);

}
