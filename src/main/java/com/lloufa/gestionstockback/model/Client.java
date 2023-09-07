package com.lloufa.gestionstockback.model;

import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Client extends AbstractEntity {

    private String nom;
    private String prenom;
    private String email;
    private String numeroTelephone;
    private String photo;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;

}
