package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
}
