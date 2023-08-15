package com.lloufa.gestionstockback.controller;

import com.lloufa.gestionstockback.controller.api.MvtStkApi;
import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.service.MvtStkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MvtStkRestController implements MvtStkApi {

    private final MvtStkService mvtStkService;

    @Autowired
    public MvtStkRestController(MvtStkService mvtStkService) {
        this.mvtStkService = mvtStkService;
    }

    public MvtStkDto save(MvtStkDto mvtStkDto) {
        return this.mvtStkService.save(mvtStkDto);
    }

    public MvtStkDto update(MvtStkDto mvtStkDto) {
        return this.mvtStkService.save(mvtStkDto);
    }

    public MvtStkDto findById(Integer id) {
        return this.mvtStkService.findById(id);
    }

    public List<MvtStkDto> findAll() {
        return this.mvtStkService.findAll();
    }

    public void delete(Integer id) {
        this.mvtStkService.delete(id);
    }
}
