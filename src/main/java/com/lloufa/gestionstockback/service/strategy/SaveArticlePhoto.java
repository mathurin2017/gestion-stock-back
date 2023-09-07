package com.lloufa.gestionstockback.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidOperationException;
import com.lloufa.gestionstockback.service.ArticleService;
import com.lloufa.gestionstockback.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDto> {

    private final PhotoService photoService;
    private final ArticleService articleService;

    @Autowired
    public SaveArticlePhoto(PhotoService photoService, ArticleService articleService) {
        this.photoService = photoService;
        this.articleService = articleService;
    }

    @Override
    public ArticleDto savePhoto(Integer id, InputStream photo, String title) throws FlickrException {
        ArticleDto articleDto = this.articleService.findById(id);
        String urlPhoto = this.photoService.save(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'article", ErrorCode.UPDATE_PHOTO_EXCEPTION);
        articleDto.setPhoto(urlPhoto);

        return this.articleService.save(articleDto);
    }
}
