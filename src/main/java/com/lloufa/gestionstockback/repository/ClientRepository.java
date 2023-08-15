package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
