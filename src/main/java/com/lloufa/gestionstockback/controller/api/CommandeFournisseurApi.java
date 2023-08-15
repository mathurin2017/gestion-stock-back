package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("commande-fournisseurs-api")
@RequestMapping("/commande-fournisseurs")
public interface CommandeFournisseurApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer une commande fournisseur", notes = "Cette méthode permet d'enregistrer une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est crée"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @ApiOperation(value = "Modifier une commande fournisseur", notes = "Cette méthode permet de modifier une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    @PutMapping
    CommandeFournisseurDto update(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette méthode permet de rechercher une commande fournisseur par son ID", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    CommandeFournisseurDto findById(@PathVariable Integer id);

    @GetMapping(value = "{code}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette méthode permet de rechercher une commande fournisseur par son CODE", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'existe dans la BDD avec le CODE fourni")
    })
    CommandeFournisseurDto findByCode(@PathVariable String code);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des commandes fournisseurs", notes = "Cette méthode permet de rechercher et renvoyer la liste des commandes fournisseur", responseContainer = "List<CommandeFournisseurDto.class>")
    @ApiResponse(code = 200, message = "La liste des commandes fournisseur a été trouvée / Une liste dans la BDD")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette méthode permet de supprimer une commande client par ID")
    @ApiResponse(code = 200, message = "La commande fournisseur a été trouvée dans la BDD")
    void delete(@PathVariable Integer id);
}
