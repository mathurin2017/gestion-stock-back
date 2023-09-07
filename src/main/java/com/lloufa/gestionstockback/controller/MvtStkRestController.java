package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.MvtStkApi;
import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.service.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class MvtStkRestController implements MvtStkApi {

    private final MvtStkService mvtStkService;

    @Autowired
    public MvtStkRestController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    @Override
    public ResponseEntity<BigDecimal> stockReelArticle(Integer idArticle) {
        return ResponseEntity.ok(this.mvtStkService.stockReelArticle(idArticle));
    }

    @Override
    public ResponseEntity<List<MvtStkDto>> mvtStkArticle(Integer idArticle) {
        return ResponseEntity.ok(this.mvtStkService.mvtStkArticle(idArticle));
    }

    @Override
    public ResponseEntity<MvtStkDto> entreeStock(MvtStkDto mvtStkDto) {
        return ResponseEntity.ok(this.mvtStkService.entreeStock(mvtStkDto));
    }

    @Override
    public ResponseEntity<MvtStkDto> sortieStock(MvtStkDto mvtStkDto) {
        return ResponseEntity.ok(this.mvtStkService.sortieStock(mvtStkDto));
    }

    @Override
    public ResponseEntity<MvtStkDto> correctionStockPositive(MvtStkDto mvtStkDto) {
        return ResponseEntity.ok(this.mvtStkService.correctionStockPositive(mvtStkDto));
    }

    @Override
    public ResponseEntity<MvtStkDto> correctionStockNegative(MvtStkDto mvtStkDto) {
        return ResponseEntity.ok(this.mvtStkService.correctionStockNegative(mvtStkDto));
    }

}
