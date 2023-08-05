package com.lloufa.gestionstockback.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class LigneVente extends AbstractEntity {

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "IdVente")
    private Vente vente;
}
