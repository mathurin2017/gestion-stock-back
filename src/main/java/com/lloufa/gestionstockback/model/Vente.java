package com.lloufa.gestionstockback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Vente extends AbstractEntity {

    private String code;

    private Instant dateVente;

    private String commentaire;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;

}
