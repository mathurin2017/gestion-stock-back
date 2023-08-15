package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("commande-clients-api")
@RequestMapping("/commande-clients")
public interface CommandeClientApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer une commande client", notes = "Cette méthode permet d'enregistrer une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est crée"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    CommandeClientDto save(@RequestBody CommandeClientDto commandeClientDto);

    @ApiOperation(value = "Modifier une commande client", notes = "Cette méthode permet de modifier une commande client", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client est modifié"),
            @ApiResponse(code = 400, message = "L'objet commande client n'est pas valide")
    })
    @PutMapping
    CommandeClientDto update(@RequestBody CommandeClientDto commandeClientDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette méthode permet de rechercher une commande client par son ID", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'existe dans la BDD avec l'ID fourni")
    })
    CommandeClientDto findById(@PathVariable Integer id);

    @GetMapping(value = "{code}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette méthode permet de rechercher une commande client par son CODE", response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a été trouvée dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client n'existe dans la BDD avec le CODE fourni")
    })
    CommandeClientDto findByCode(@PathVariable String code);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des commandes clients", notes = "Cette méthode permet de rechercher et renvoyer la liste des commandes client", responseContainer = "List<CommandeClientDto.class>")
    @ApiResponse(code = 200, message = "La liste des commandes client a été trouvée / Une liste dans la BDD")
    List<CommandeClientDto> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher une commande client", notes = "Cette méthode permet de supprimer une commande client par ID")
    @ApiResponse(code = 200, message = "La commande client a été trouvée dans la BDD")
    void delete(@PathVariable Integer id);
}
