package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {

    List<LigneCommandeClient> findAllByCommandeClientId(Integer idClient);

    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);

}
