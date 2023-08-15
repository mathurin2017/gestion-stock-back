package com.lloufa.gestionstockback.service;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface PhotoService {

    String save(InputStream photo, String title) throws FlickrException;
}
