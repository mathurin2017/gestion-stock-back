package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.MvtStkDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {

    BigDecimal stockReelArticle(Integer idArticle);

    List<MvtStkDto> mvtStkArticle(Integer idArticle);

    MvtStkDto entreeStock(MvtStkDto mvtStkDto);

    MvtStkDto sortieStock(MvtStkDto mvtStkDto);

    MvtStkDto correctionStockPositive(MvtStkDto mvtStkDto);

    MvtStkDto correctionStockNegative(MvtStkDto mvtStkDto);

}
