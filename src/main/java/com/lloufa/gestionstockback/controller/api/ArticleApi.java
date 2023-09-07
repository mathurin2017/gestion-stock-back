package com.lloufa.gestionstockback.controller.api;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.dto.LigneVenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto);

    @PostMapping(value = "/photo/{id}/{title}")
    @ApiOperation(value = "Enregistrer une photo article", notes = "Cette méthode permet d'enregistrer une photo article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article est crée"),
            @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
    ResponseEntity<ArticleDto> savePhoto(@PathVariable Integer id, @RequestPart("file") MultipartFile photo, @PathVariable String title) throws FlickrException, IOException;

    @PutMapping
    @ApiOperation(value = "Modifier un article", notes = "Cette méthode permet de modifier un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article est modifié"),
            @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
    ResponseEntity<ArticleDto> update(@RequestBody ArticleDto articleDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de rechercher un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<ArticleDto> findById(@PathVariable Integer id);

    @GetMapping(value = "{code}")
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de rechercher un article par son CODE", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<ArticleDto> findByCode(@PathVariable String code);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des articles", notes = "Cette méthode permet de rechercher et renvoyer la liste des articles", responseContainer = "List<ArticleDto.class>")
    @ApiResponse(code = 200, message = "La liste des articles a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<ArticleDto>> findAll();

    @GetMapping(value = "/category/{idCategory}")
    @ApiOperation(value = "Renvoi la liste des articles par catégorie", notes = "Cette méthode permet de rechercher et renvoyer la liste des articles par catégorie", responseContainer = "List<ArticleDto.class>")
    @ApiResponse(code = 200, message = "La liste des articles a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<ArticleDto>> findAllArticleByIdCategorie(@PathVariable Integer idCategory);

    @GetMapping(value = "/historique/vente/{idArticle}")
    @ApiOperation(value = "Renvoi la liste des lignes de ventes par article", notes = "Cette méthode permet de rechercher et renvoyer la liste des lignes de ventes par article", responseContainer = "List<LigneVenteDto.class>")
    @ApiResponse(code = 200, message = "La liste des lignes de ventes a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<LigneVenteDto>> findHistoriqueVente(@PathVariable Integer idArticle);

    @GetMapping(value = "/historique/commandeClient/{idArticle}")
    @ApiOperation(value = "Renvoi la liste des commandes de clients par article", notes = "Cette méthode permet de rechercher et renvoyer la liste des commandes de clients par article", responseContainer = "List<LigneCommandeClientDto.class>")
    @ApiResponse(code = 200, message = "La liste des commandes de clients a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<LigneCommandeClientDto>> findHistoriqueCommandeClient(@PathVariable Integer idArticle);

    @GetMapping(value = "/historique/commandeFournisseur/{idArticle}")
    @ApiOperation(value = "Renvoi la liste des des commandes de fournisseurs par article", notes = "Cette méthode permet de rechercher et renvoyer la liste des commandes de fournisseurs par article", responseContainer = "List<LigneCommandeFournisseurDto.class>")
    @ApiResponse(code = 200, message = "La liste des commandes de fournisseurs a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<LigneCommandeFournisseurDto>> findHistoriqueCommandeFournisseur(@PathVariable Integer idArticle);

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de supprimer un article par ID")
    @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);

}
