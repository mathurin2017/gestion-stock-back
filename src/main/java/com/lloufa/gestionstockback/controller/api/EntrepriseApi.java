package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("entreprises-api")
@RequestMapping("/entreprises")
public interface EntrepriseApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer une entreprise", notes = "Cette méthode permet d'enregistrer une entreprise", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise est crée"),
            @ApiResponse(code = 400, message = "L'objet entreprise n'est pas valide")
    })
    ResponseEntity<EntrepriseDto> save(@RequestBody EntrepriseDto entrepriseDto);

    @ApiOperation(value = "Modifier une entreprise", notes = "Cette méthode permet de modifier une entreprise", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise est modifié"),
            @ApiResponse(code = 400, message = "L'objet entreprise n'est pas valide")
    })
    @PutMapping
    ResponseEntity<EntrepriseDto> update(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une entreprise", notes = "Cette méthode permet de rechercher une entreprise par son ID", response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune entreprise n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<EntrepriseDto> findById(@PathVariable Integer id);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des entreprises", notes = "Cette méthode permet de rechercher et renvoyer la liste des entreprises", responseContainer = "List<EntrepriseDto.class>")
    @ApiResponse(code = 200, message = "La liste des entreprises a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<EntrepriseDto>> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une entreprise", notes = "Cette méthode permet de supprimer une entreprise par ID")
    @ApiResponse(code = 200, message = "L'entreprise a été trouvée dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);

}
