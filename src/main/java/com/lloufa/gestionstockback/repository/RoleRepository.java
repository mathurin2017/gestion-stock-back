package com.lloufa.gestionstockback.repository;

import com.lloufa.gestionstockback.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findRoleByNom(String nom);

}
