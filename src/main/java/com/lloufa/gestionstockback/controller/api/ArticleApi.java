package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.ArticleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("articles-api")
@RequestMapping("/articles")
public interface ArticleApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer un article", notes = "Cette méthode permet d'enregistrer un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article est crée"),
            @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @ApiOperation(value = "Modifier un article", notes = "Cette méthode permet de modifier un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article est modifié"),
            @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
    @PutMapping
    ArticleDto update(@RequestBody ArticleDto articleDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de rechercher un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ArticleDto findById(@PathVariable Integer id);

    @GetMapping(value = "{code}")
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de rechercher un article par son CODE", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    ArticleDto findByCode(@PathVariable String code);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des articles", notes = "Cette méthode permet de rechercher et renvoyer la liste des articles", responseContainer = "List<ArticleDto.class>")
    @ApiResponse(code = 200, message = "La liste des articles a été trouvée / Une liste dans la BDD")
    List<ArticleDto> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de supprimer un article par ID")
    @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD")
    void delete(@PathVariable Integer id);
}
