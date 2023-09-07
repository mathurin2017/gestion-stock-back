package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Integer> {

    List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(Integer idFournisseur);

    List<LigneCommandeFournisseur> findAllByArticleId(Integer idArticle);

}
