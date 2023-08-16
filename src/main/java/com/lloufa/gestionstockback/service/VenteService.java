package com.lloufa.gestionstockback.service;

import com.lloufa.gestionstockback.dto.VenteDto;

import java.util.List;

public interface VenteService {

    VenteDto save(VenteDto venteDto);

    VenteDto findById(Integer id);

    VenteDto findByCode(String code);

    List<VenteDto> findAll();

    void delete(Integer id);

}
