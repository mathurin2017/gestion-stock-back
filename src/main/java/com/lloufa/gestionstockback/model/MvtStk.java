package com.lloufa.gestionstockback.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class MvtStk extends AbstractEntity {

    private Instant dateMvt;

    private BigDecimal quantite;

    private TypeMvt typeMvt;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
