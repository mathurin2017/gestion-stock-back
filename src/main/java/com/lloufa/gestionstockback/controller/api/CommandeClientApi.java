package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.CommandeClientDto;
import com.lloufa.gestionstockback.dto.LigneCommandeClientDto;
import com.lloufa.gestionstockback.model.EtatCommande;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api("commandes-clients-api")
@RequestMapping("/commandesClients")
public interface CommandeClientApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer une commande client", notes = "Cette méthode permet d'enregistrer une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est crée"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto);

    @ApiOperation(value = "Modifier une commande client", notes = "Cette méthode permet de modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    @PutMapping
    ResponseEntity<CommandeClientDto> update(@RequestBody CommandeClientDto commandeClientDto);

    @ApiOperation(value = "Modifier l'état de la commande client", notes = "Cette méthode permet de modifier l'état d'une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    @PutMapping(value = "{id}/{etatCommande}")
    ResponseEntity<CommandeClientDto> updateEtat(@PathVariable Integer id, @PathVariable EtatCommande etatCommande);

    @ApiOperation(value = "Modifier la quantité de la commande client", notes = "Cette méthode permet de modifier la quantité d'une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    @PutMapping(value = "{idCommande}/{idLigneCommande}/{quantite}")
    ResponseEntity<CommandeClientDto> updateQuantite(@PathVariable Integer idCommande, @PathVariable Integer idLigneCommande, @PathVariable BigDecimal quantite);

    @ApiOperation(value = "Modifier le client de la commande client", notes = "Cette méthode permet de modifier le client d'une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    @PutMapping(value = "{id}/{idClient}")
    ResponseEntity<CommandeClientDto> updateClient(@PathVariable Integer id, @PathVariable Integer idClient);

    @ApiOperation(value = "Modifier l'article de la commande client", notes = "Cette méthode permet de modifier l'article d'une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    @PutMapping(value = "{idCommande}/{idLigneCommande}/{idArticle}")
    ResponseEntity<CommandeClientDto> updateArticle(@PathVariable Integer idCommande, @PathVariable Integer idLigneCommande, @PathVariable Integer idArticle);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette méthode permet de rechercher une commande client par son ID", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'existe dans la BDD avec l'ID fourni")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id);

    @GetMapping(value = "{code}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette méthode permet de rechercher une commande client par son CODE", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'existe dans la BDD avec le CODE fourni")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable String code);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des commandes clients", notes = "Cette méthode permet de rechercher et renvoyer la liste des commandes clients", responseContainer = "List<CommandeClientDto.class>")
    @ApiResponse(code = 200, message = "La liste des commandes clients a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @GetMapping(value = "/ligneCommandeClient/{idClient}")
    @ApiOperation(value = "Renvoi la liste des lignes de commandes d'un client", notes = "Cette méthode permet de rechercher et renvoyer la liste des lignes de commandes d'un client", responseContainer = "List<LigneCommandeClientDto.class>")
    @ApiResponse(code = 200, message = "La liste des lignes de commandes d'un client a été trouvée / Une liste dans la BDD")
    ResponseEntity<List<LigneCommandeClientDto>> findAllLigneCommandeClientByCommandeClientId(@PathVariable Integer idClient);

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette méthode permet de supprimer une commande client par ID")
    @ApiResponse(code = 200, message = "La commande client a été trouvée dans la BDD")
    ResponseEntity<?> delete(@PathVariable Integer id);

    @DeleteMapping(value = "{idCommande}/{idLigneCommande}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette méthode permet de supprimer une commande client par ID")
    @ApiResponse(code = 200, message = "La commande client a été trouvée dans la BDD")
    ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable Integer idCommande, @PathVariable Integer idLigneCommande);

}
