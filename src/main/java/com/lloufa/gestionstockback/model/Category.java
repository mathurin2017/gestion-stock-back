package com.lloufa.gestionstockback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Category extends AbstractEntity {

    private String code;

    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

}
