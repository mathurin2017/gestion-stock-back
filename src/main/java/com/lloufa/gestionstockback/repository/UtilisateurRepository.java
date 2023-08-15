package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
