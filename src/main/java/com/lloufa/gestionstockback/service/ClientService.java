package com.lloufa.gestionstockback.service;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ClientDto;

import java.io.InputStream;
import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException;

    ClientDto findById(Integer id);

    List<ClientDto> findAll();

    void delete(Integer id);

}
