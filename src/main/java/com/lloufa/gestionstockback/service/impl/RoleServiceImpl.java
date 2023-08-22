package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.RoleDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.RoleMapping;
import com.lloufa.gestionstockback.model.Role;
import com.lloufa.gestionstockback.repository.RoleRepository;
import com.lloufa.gestionstockback.service.RoleService;
import com.lloufa.gestionstockback.validator.RoleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        List<String> errors = RoleValidator.validate(roleDto);
        if (!errors.isEmpty()) {
            log.error("Role is not valid {}", roleDto);
            throw new InvalidEntityException("Le role n'est pas valide ", ErrorCode.ROLE_NOT_VALID, errors);
        }
        Role role = this.roleRepository.save(RoleMapping.toEntity(roleDto));

        return RoleMapping.fromEntity(role);
    }

    @Override
    public RoleDto findById(Integer id) {
        if (null == id) {
            log.error("Article ID is null");
            return null;
        }

        return this.roleRepository.findById(id)
                .map(RoleMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun role avec ID " + id + " n'a été trouvé dans la BDD", ErrorCode.ROLE_NOT_FOUND));
    }

    @Override
    public RoleDto findByNom(String nom) {
        if (null == nom) {
            log.error("Article NOM is null");
            return null;
        }

        return this.roleRepository.findRoleByNom(nom)
                .map(RoleMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun article avec le NOM " + nom + " n'a été trouvé dans la BDD", ErrorCode.ROLE_NOT_FOUND));
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roleList = this.roleRepository.findAll();
        log.error("Number role in BDD is {}", roleList.size());
        return roleList.stream().map(RoleMapping::fromEntity).collect(Collectors.toList());
    }

}
