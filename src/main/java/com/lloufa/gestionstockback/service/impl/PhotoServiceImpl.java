package com.lloufa.gestionstockback.service.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.lloufa.gestionstockback.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    private final Flickr flickr;

    @Autowired
    public PhotoServiceImpl(Flickr flickr) {
        this.flickr = flickr;
    }

    @Override
    public String save(InputStream photo, String title) throws FlickrException {
        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);
        String photoId = this.flickr.getUploader().upload(photo, uploadMetaData);

        return this.flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
    }

}
