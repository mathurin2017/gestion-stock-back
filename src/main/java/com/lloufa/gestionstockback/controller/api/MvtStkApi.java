package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.MvtStkDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api("mvt-stks-api")
@RequestMapping("/mvtStks")
public interface MvtStkApi {

    @GetMapping(value = "/stockReel/{idArticle}")
    @ApiOperation(value = "Renvoyer le nombre total de mvtStk", notes = "Cette méthode permet de renvoyer le nombre de mvtStk", response = BigDecimal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre total de mvtStk a été trouvé dans la BDD"),
            @ApiResponse(code = 400, message = "Aucun nombre mvtStk n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<BigDecimal> stockReelArticle(@PathVariable Integer idArticle);

    @GetMapping(value = "/article/{idArticle}")
    @ApiOperation(value = "Renvoi la liste des mvtStks id article", notes = "Cette méthode permet de rechercher la liste des mvtStks par Article ID", responseContainer = "List<MvtStkDto.class>")
    @ApiResponse(code = 200, message = "La liste des mvtStks par IdArticle a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<MvtStkDto>> mvtStkArticle(@PathVariable Integer idArticle);

    @PostMapping(value = "/entree")
    @ApiOperation(value = "Enregistrer une entrée mvtStk", notes = "Cette méthode permet d'enregistrer une entrée mvtStk", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet mvtStk est crée"),
            @ApiResponse(code = 400, message = "L'objet mvtStk n'est pas valide")
    })
    ResponseEntity<MvtStkDto> entreeStock(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = "/sortie")
    @ApiOperation(value = "Enregistrer une entrée mvtStk", notes = "Cette méthode permet d'enregistrer une sortie mvtStk", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet mvtStk est crée"),
            @ApiResponse(code = 400, message = "L'objet mvtStk n'est pas valide")
    })
    ResponseEntity<MvtStkDto> sortieStock(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = "/correctionPositive")
    @ApiOperation(value = "Enregistrer une entrée mvtStk", notes = "Cette méthode permet d'enregistrer une correction positive mvtStk", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet mvtStk est crée"),
            @ApiResponse(code = 400, message = "L'objet mvtStk n'est pas valide")
    })
    ResponseEntity<MvtStkDto> correctionStockPositive(@RequestBody MvtStkDto mvtStkDto);

    @PostMapping(value = "/correctionNegative")
    @ApiOperation(value = "Enregistrer une entrée mvtStk", notes = "Cette méthode permet d'enregistrer une correction négative mvtStk", response = MvtStkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet mvtStk est crée"),
            @ApiResponse(code = 400, message = "L'objet mvtStk n'est pas valide")
    })
    ResponseEntity<MvtStkDto> correctionStockNegative(@RequestBody MvtStkDto mvtStkDto);

}
