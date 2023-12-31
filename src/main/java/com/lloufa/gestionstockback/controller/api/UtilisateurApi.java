package com.lloufa.gestionstockback.controller.api;

import com.flickr4java.flickr.FlickrException;
import com.lloufa.gestionstockback.dto.ArticleDto;
import com.lloufa.gestionstockback.dto.ChangePasswordUserDto;
import com.lloufa.gestionstockback.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api("utilisateurs-api")
@RequestMapping("/utilisateurs")
public interface UtilisateurApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer un utilisateur", notes = "Cette méthode permet d'enregistrer un utilisateur", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur est crée"),
            @ApiResponse(code = 400, message = "L'objet utilisateur n'est pas valide")
    })
    ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto utilisateurDto);

    @PostMapping(value = "/photo/{id}/{title}")
    @ApiOperation(value = "Enregistrer une photo utilisateur", notes = "Cette méthode permet d'enregistrer une photo utilisateur", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur est crée"),
            @ApiResponse(code = 400, message = "L'objet utilisateur n'est pas valide")
    })
    ResponseEntity<UtilisateurDto> savePhoto(@PathVariable Integer id, @RequestPart("file") MultipartFile photo, @PathVariable String title) throws IOException, FlickrException;

    @ApiOperation(value = "Modifier un utilisateur", notes = "Cette méthode permet de modifier un utilisateur", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur est modifié"),
            @ApiResponse(code = 400, message = "L'objet utilisateur n'est pas valide")
    })
    @PutMapping
    ResponseEntity<UtilisateurDto> update(@RequestBody UtilisateurDto utilisateurDto);

    @ApiOperation(value = "Modifier un mot de passe utilisateur", notes = "Cette méthode permet de modifier un mot de passe utilisateur", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet utilisateur est modifié"),
            @ApiResponse(code = 400, message = "L'objet utilisateur n'est pas valide")
    })
    @PutMapping(value = "/changePassword")
    ResponseEntity<UtilisateurDto> updatePassword(@RequestBody ChangePasswordUserDto changePasswordUserDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un utilisateur", notes = "Cette méthode permet de rechercher un utilisateur par son ID", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le utilisateur a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun utilisateur n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<UtilisateurDto> findById(@PathVariable Integer id);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des utilisateurs", notes = "Cette méthode permet de rechercher et renvoyer la liste des utilisateurs", responseContainer = "List<UtilisateurDto.class>")
    @ApiResponse(code = 200, message = "La liste des utilisateurs a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<UtilisateurDto>> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un utilisateur", notes = "Cette méthode permet de supprimer un utilisateur par ID")
    @ApiResponse(code = 200, message = "L'utilisateur a été trouvé dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);

}
