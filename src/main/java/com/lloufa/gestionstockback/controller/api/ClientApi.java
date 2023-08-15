package com.lloufa.gestionstockback.controller.api;

import com.lloufa.gestionstockback.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("clients-api")
@RequestMapping("/clients")
public interface ClientApi {

    @PostMapping
    @ApiOperation(value = "Enregistrer un client", notes = "Cette méthode permet d'enregistrer un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client est crée"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")
    })
    ClientDto save(@RequestBody ClientDto clientDto);

    @ApiOperation(value = "Modifier un client", notes = "Cette méthode permet de modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client est modifié"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")
    })
    @PutMapping
    ClientDto update(@RequestBody ClientDto clientDto);

    @GetMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un client", notes = "Cette méthode permet de rechercher un client par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun client n'existe dans la BDD avec l'ID fourni")
    })
    ClientDto findById(@PathVariable Integer id);

    @GetMapping
    @ApiOperation(value = "Renvoi la liste des clients", notes = "Cette méthode permet de rechercher et renvoyer la liste des clients", responseContainer = "List<ClientDto.class>")
    @ApiResponse(code = 200, message = "La liste des clients a été trouvé / Une liste dans la BDD")
    List<ClientDto> findAll();

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "Rechercher un client", notes = "Cette méthode permet de supprimer un client par ID")
    @ApiResponse(code = 200, message = "Le client a été trouvé dans la BDD")
    void delete(@PathVariable Integer id);
}
