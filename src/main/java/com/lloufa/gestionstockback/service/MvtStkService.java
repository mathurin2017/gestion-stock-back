package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.MvtStkDto;

import java.util.List;

public interface MvtStkService {

    MvtStkDto save(MvtStkDto mvtStkDto);

    MvtStkDto findById(Integer id);

    List<MvtStkDto> findAll();

    void delete(Integer id);

}
