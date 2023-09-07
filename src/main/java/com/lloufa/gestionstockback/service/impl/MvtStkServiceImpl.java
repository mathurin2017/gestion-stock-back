package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.MvtStkMapping;
import com.lloufa.gestionstockback.model.MvtStk;
import com.lloufa.gestionstockback.model.TypeMvtStk;
import com.lloufa.gestionstockback.repository.MvtStkRepository;
import com.lloufa.gestionstockback.service.ArticleService;
import com.lloufa.gestionstockback.service.MvtStkService;
import com.lloufa.gestionstockback.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

    private final MvtStkRepository mvtStkRepository;
    private final ArticleService articleService;

    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository, ArticleService articleService) {
        this.mvtStkRepository = mvtStkRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        this.articleService.findById(idArticle);
        return this.mvtStkRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStkDto> mvtStkArticle(Integer idArticle) {
        List<MvtStk> mvtStkList = this.mvtStkRepository.findAllByArticleId(idArticle);
        log.error("Number mvtStk by article in BDD is {}", mvtStkList.size());
        return mvtStkList.stream().map(MvtStkMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public MvtStkDto entreeStock(MvtStkDto mvtStkDto) {
        return this.stock(mvtStkDto, TypeMvtStk.ENTREE);
    }

    @Override
    public MvtStkDto sortieStock(MvtStkDto mvtStkDto) {
        return this.stock(mvtStkDto, TypeMvtStk.SORTIE);
    }

    @Override
    public MvtStkDto correctionStockPositive(MvtStkDto mvtStkDto) {
        return this.stock(mvtStkDto, TypeMvtStk.CORRECTION_POS);
    }

    @Override
    public MvtStkDto correctionStockNegative(MvtStkDto mvtStkDto) {
        return this.stock(mvtStkDto, TypeMvtStk.CORRECTION_NEG);
    }

    private MvtStkDto stock(MvtStkDto mvtStkDto, TypeMvtStk typeMvtStk) {
        List<String> errors = MvtStkValidator.validate(mvtStkDto);
        if (!errors.isEmpty()) {
            log.error("MvtStk is not valid {}", mvtStkDto);
            throw new InvalidEntityException("Le mvtStk n'est pas valide ", ErrorCode.MVT_STK_NOT_VALID, errors);
        }

        if (TypeMvtStk.ENTREE.equals(typeMvtStk) || TypeMvtStk.CORRECTION_POS.equals(typeMvtStk)) mvtStkDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStkDto.getQuantite().doubleValue())));
        else if (TypeMvtStk.SORTIE.equals(typeMvtStk) || TypeMvtStk.CORRECTION_NEG.equals(typeMvtStk)) mvtStkDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStkDto.getQuantite().doubleValue()) * -1));

        mvtStkDto.setTypeMvtStk(typeMvtStk);
        MvtStk mvtStk = this.mvtStkRepository.save(MvtStkMapping.toEntity(mvtStkDto));

        return MvtStkMapping.fromEntity(mvtStk);
    }

}
