package com.lloufa.gestionstockback.model;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adresse {

    private String adresse1;

    private String adresse2;

    private String ville;

    private String code_postal;

    private String pays;
}
