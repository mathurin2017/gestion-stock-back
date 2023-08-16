package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.MvtStkApi;
import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.service.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MvtStkRestController implements MvtStkApi {

    private final MvtStkService mvtStkService;

    @Autowired
    public MvtStkRestController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    public ResponseEntity<MvtStkDto> save(MvtStkDto mvtStkDto) {
        return ResponseEntity.ok(this.mvtStkService.save(mvtStkDto));
    }

    public ResponseEntity<MvtStkDto> update(MvtStkDto mvtStkDto) {
        return ResponseEntity.ok(this.mvtStkService.save(mvtStkDto));
    }

    public ResponseEntity<MvtStkDto> findById(Integer id) {
        return ResponseEntity.ok(this.mvtStkService.findById(id));
    }

    public ResponseEntity<List<MvtStkDto>> findAll() {
        return ResponseEntity.ok(this.mvtStkService.findAll());
    }

    public ResponseEntity<?> delete(Integer id) {
        this.mvtStkService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
