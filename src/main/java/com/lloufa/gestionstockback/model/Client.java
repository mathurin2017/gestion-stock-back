package com.lloufa.gestionstockback.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Client extends AbstractEntity {

    private String nom;

    private String prenom;

    @Embedded
    private Adresse adresse;

    private String email;

    private String numeroTelephone;

    private String photo;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
