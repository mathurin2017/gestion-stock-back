package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.EntrepriseDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.EntrepriseMapping;
import com.lloufa.gestionstockback.model.Entreprise;
import com.lloufa.gestionstockback.repository.EntrepriseRepository;
import com.lloufa.gestionstockback.service.EntrepriseService;
import com.lloufa.gestionstockback.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide ", ErrorCode.ENTREPRISE_NOT_VALID, errors);
        }
        Entreprise entreprise = this.entrepriseRepository.save(EntrepriseMapping.toEntity(entrepriseDto));

        return EntrepriseMapping.fromEntity(entreprise);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (null == id) {
            log.error("Entreprise ID is null");
            return null;
        }

        return this.entrepriseRepository.findById(id)
                .map(EntrepriseMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune entreprise avec ID " + id + " n'a été trouvée dans la BDD", ErrorCode.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        List<Entreprise> entrepriseList = this.entrepriseRepository.findAll();
        log.error("Number entreprise in BDD is {}", entrepriseList.size());
        return entrepriseList.stream().map(EntrepriseMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        EntrepriseDto entrepriseDto = findById(id);
        if (null != entrepriseDto) this.entrepriseRepository.delete(EntrepriseMapping.toEntity(entrepriseDto));
    }

}
