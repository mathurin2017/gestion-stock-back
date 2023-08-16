package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("categories-api")
@RequestMapping("/categories")
public interface CategorieApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer une catégorie", notes = "Cette méthode permet d'enregistrer une catégorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie est crée"),
            @ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")
    })
    ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto);

    @ApiOperation(value = "Modifier une catégorie", notes = "Cette méthode permet de modifier une catégorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie est modifié"),
            @ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")
    })
    @PutMapping
    ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une catégorie", notes = "Cette méthode permet de rechercher une catégorie par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La catégorie a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune catégorie n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CategoryDto> findById(@PathVariable Integer id);

    @GetMapping(value = "{code}")
    @ApiOperation(value = "Rechercher une catégorie", notes = "Cette méthode permet de rechercher une catégorie par son CODE", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La catégorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune catégorie n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<CategoryDto> findByCode(@PathVariable String code);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des catégories", notes = "Cette méthode permet de rechercher et renvoyer la liste des catégories", responseContainer = "List<CategoryDto.class>")
    @ApiResponse(code = 200, message = "La liste des catégories a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<CategoryDto>> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une catégorie", notes = "Cette méthode permet de supprimer une catégorie par ID")
    @ApiResponse(code = 200, message = "La catégorie a été trouvée dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);

}
