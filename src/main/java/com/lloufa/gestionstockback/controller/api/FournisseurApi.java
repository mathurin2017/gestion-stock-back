package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("fournisseurs-api")
@RequestMapping("/fournisseurs")
public interface FournisseurApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer un fournisseur", notes = "Cette méthode permet d'enregistrer un fournisseur", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur est crée"),
            @ApiResponse(code = 400, message = "L'objet fournisseur n'est pas valide")
    })
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);

    @ApiOperation(value = "Modifier un fournisseur", notes = "Cette méthode permet de modifier un fournisseur", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur est modifié"),
            @ApiResponse(code = 400, message = "L'objet fournisseur n'est pas valide")
    })
    @PutMapping
    ResponseEntity<FournisseurDto> update(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un fournisseur", notes = "Cette méthode permet de rechercher un fournisseur par son ID", response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<FournisseurDto> findById(@PathVariable Integer id);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des fournisseurs", notes = "Cette méthode permet de rechercher et renvoyer la liste des fournisseurs", responseContainer = "List<FournisseurDto.class>")
    @ApiResponse(code = 200, message = "La liste des fournisseurs a été trouvé / Une liste dans la BDD")
    ResponseEntity<List<FournisseurDto>> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un fournisseur", notes = "Cette méthode permet de supprimer un fournisseur par ID")
    @ApiResponse(code = 200, message = "Le fournisseur a été trouvé dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);

}
