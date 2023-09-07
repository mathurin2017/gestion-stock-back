package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.CommandeFournisseurDto;
import com.lloufa.gestionstockback.dto.LigneCommandeFournisseurDto;
import com.lloufa.gestionstockback.model.EtatCommande;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api("commandes-fournisseurs-api")
@RequestMapping("/commandesFournisseurs")
public interface CommandeFournisseurApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer une commande fournisseur", notes = "Cette méthode permet d'enregistrer une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est crée"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    ResponseEntity<CommandeFournisseurDto> save(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @ApiOperation(value = "Modifier une commande fournisseur", notes = "Cette méthode permet de modifier une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    @PutMapping
    ResponseEntity<CommandeFournisseurDto> update(@RequestBody CommandeFournisseurDto commandeFournisseurDto);

    @ApiOperation(value = "Modifier l'état de la commande fournisseur", notes = "Cette méthode permet de modifier l'état d'une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    @PutMapping(value = "{id}/{etatCommande}")
    ResponseEntity<CommandeFournisseurDto> updateEtat(@PathVariable Integer id, @PathVariable EtatCommande etatCommande);

    @ApiOperation(value = "Modifier la quantité de la commande fournisseur", notes = "Cette méthode permet de modifier la quantité d'une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    @PutMapping(value = "{idCommande}/{idLigneCommande}/{quantite}")
    ResponseEntity<CommandeFournisseurDto> updateQuantite(@PathVariable Integer idCommande, @PathVariable Integer idLigneCommande, @PathVariable BigDecimal quantite);

    @ApiOperation(value = "Modifier le fournisseur de la commande fournisseur", notes = "Cette méthode permet de modifier le fournisseur d'une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    @PutMapping(value = "{id}/{idFournisseur}")
    ResponseEntity<CommandeFournisseurDto> updateFournisseur(@PathVariable Integer id, @PathVariable Integer idFournisseur);

    @ApiOperation(value = "Modifier l'article de la commande fournisseur", notes = "Cette méthode permet de modifier l'article d'une commande fournisseur", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    @PutMapping(value = "{idCommande}/{idLigneCommande}/{idArticle}")
    ResponseEntity<CommandeFournisseurDto> updateArticle(@PathVariable Integer idCommande, @PathVariable Integer idLigneCommande, @PathVariable Integer idArticle);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette méthode permet de rechercher une commande fournisseur par son ID", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CommandeFournisseurDto> findById(@PathVariable Integer id);

    @GetMapping(value = "{code}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette méthode permet de rechercher une commande fournisseur par son CODE", response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<CommandeFournisseurDto> findByCode(@PathVariable String code);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des commandes fournisseurs", notes = "Cette méthode permet de rechercher et renvoyer la liste des commandes fournisseurs", responseContainer = "List<CommandeFournisseurDto.class>")
    @ApiResponse(code = 200, message = "La liste des commandes fournisseurs a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<CommandeFournisseurDto>> findAll();

    @GetMapping(value = "/ligneCommandeFournisseur/{idFournisseur}")
    @ApiOperation(value = "Renvoi la liste des lignes de commandes d'un fournisseur", notes = "Cette méthode permet de rechercher et renvoyer la liste des lignes de commandes d'un fournisseur", responseContainer = "List<LigneCommandeFournisseurDto.class>")
    @ApiResponse(code = 200, message = "La liste des lignes de commandes d'un fournisseur a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<LigneCommandeFournisseurDto>> findAllLigneCommandeFournisseurByCommandeFournisseurId(@PathVariable Integer idFournisseur);

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette méthode permet de supprimer une commande fournisseur par ID")
    @ApiResponse(code = 200, message = "La commande fournisseur a été trouvée dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);

    @DeleteMapping(value = "{idCommande}/{idLigneCommande}")
    @ApiOperation(value = "Rechercher une commande fournisseur", notes = "Cette méthode permet de supprimer une commande fournisseur par ID")
    @ApiResponse(code = 200, message = "La commande fournisseur a été trouvée dans la BDD")
    ResponseEntity<CommandeFournisseurDto> deleteArticle(@PathVariable Integer idCommande, @PathVariable Integer idLigneCommande);

}
