package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.ClientDto;
import com.lloufa.gestionstockback.model.Client;

public class ClientMapping {

    public static ClientDto fromEntity(Client client) {
        if (null == client) {
            return null;
            // TODO throw an execption
        }

        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .adresseDto(AdresseMapping.fromEntity(client.getAdresse()))
                .email(client.getEmail())
                .numeroTelephone(client.getNumeroTelephone())
                .photo(client.getPhoto())
                .build();
    }

    public static Client toEntity(ClientDto clientDto) {
        if (null == clientDto) {
            return null;
            // TODO throw an execption
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(AdresseMapping.toEntity(clientDto.getAdresseDto()));
        client.setEmail(clientDto.getEmail());
        client.setNumeroTelephone(clientDto.getNumeroTelephone());
        client.setPrenom(clientDto.getPrenom());

        return client;
    }

}
