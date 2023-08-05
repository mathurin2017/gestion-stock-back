package com.lloufa.gestionstockback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Utilisateur extends AbstractEntity {

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String motDePasee;

    @Embedded
    private Adresse adresse;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "idEntreprise")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "utilisateur")
    private List<Role> roles;
}
