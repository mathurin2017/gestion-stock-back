package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.VenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("ventes-api")
@RequestMapping("/ventes")
public interface VenteApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer une vente", notes = "Cette méthode permet d'enregistrer une vente", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente est crée"),
            @ApiResponse(code = 400, message = "L'objet vente n'est pas valide")
    })
    ResponseEntity<VenteDto> save(@RequestBody VenteDto venteDto);

    @ApiOperation(value = "Modifier une vente", notes = "Cette méthode permet de modifier une vente", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente est modifié"),
            @ApiResponse(code = 400, message = "L'objet vente n'est pas valide")
    })
    @PutMapping
    ResponseEntity<VenteDto> update(@RequestBody VenteDto venteDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une vente", notes = "Cette méthode permet de rechercher une vente par son ID", response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune vente n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<VenteDto> findById(@PathVariable Integer id);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des ventes", notes = "Cette méthode permet de rechercher et renvoyer la liste des ventes", responseContainer = "List<VenteDto.class>")
    @ApiResponse(code = 200, message = "La liste des ventes a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<VenteDto>> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une vente", notes = "Cette méthode permet de supprimer une vente par ID")
    @ApiResponse(code = 200, message = "La vente a été trouvée dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);
    
}
