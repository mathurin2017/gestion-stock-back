package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.MvtStkDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("mvt-stks-api")
@RequestMapping("/mvt-stks")
public interface MvtStkApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer un mvtStk", notes = "Cette méthode permet d'enregistrer un mvtStk", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet mvtStk est crée"),
            @ApiResponse(code = 400, message = "L'objet mvtStk n'est pas valide")
    })
    MvtStkDto save(@RequestBody MvtStkDto mvtStkDto);

    @ApiOperation(value = "Modifier un mvtStk", notes = "Cette méthode permet de modifier un mvtStk", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet mvtStk est modifié"),
            @ApiResponse(code = 400, message = "L'objet mvtStk n'est pas valide")
    })
    @PutMapping
    MvtStkDto update(@RequestBody MvtStkDto mvtStkDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un mvtStk", notes = "Cette méthode permet de rechercher un mvtStk par son ID", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mvtStk a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun mvtStk n'existe dans la BDD avec l'ID fourni")
    })
    MvtStkDto findById(@PathVariable Integer id);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des mvtStks", notes = "Cette méthode permet de rechercher et renvoyer la liste des mvtStks", responseContainer = "List<MvtStkDto.class>")
    @ApiResponse(code = 200, message = "La liste des mvtStks a été trouvée / Une liste dans la BDD")
    List<MvtStkDto> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un mvtStk", notes = "Cette méthode permet de supprimer un mvtStk par ID")
    @ApiResponse(code = 200, message = "Le mvtStk a été trouvé dans la BDD")
    void delete(@PathVariable Integer id);
}
