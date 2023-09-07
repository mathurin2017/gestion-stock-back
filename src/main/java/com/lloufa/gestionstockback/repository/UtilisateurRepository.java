package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.Role;
import com.lloufa.gestionstockback.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findUtilisateurByEmail(String email);

}
