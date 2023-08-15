package com.lloufa.gestionstockback.service.impl;

import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.exception.EntityNotFoundException;
import com.lloufa.gestionstockback.exception.ErrorCode;
import com.lloufa.gestionstockback.exception.InvalidEntityException;
import com.lloufa.gestionstockback.mapping.MvtStkMapping;
import com.lloufa.gestionstockback.model.MvtStk;
import com.lloufa.gestionstockback.repository.MvtStkRepository;
import com.lloufa.gestionstockback.service.MvtStkService;
import com.lloufa.gestionstockback.validator.MvtStkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStkServiceImpl implements MvtStkService {

    private final MvtStkRepository mvtStkRepository;

    @Autowired
    public MvtStkServiceImpl(MvtStkRepository mvtStkRepository) {
        this.mvtStkRepository = mvtStkRepository;
    }

    @Override
    public MvtStkDto save(MvtStkDto mvtStkDto) {
        List<String> errors = MvtStkValidator.validate(mvtStkDto);
        if (!errors.isEmpty()) {
            log.error("MvtStk is not valid {}", mvtStkDto);
            throw new InvalidEntityException("Le mvtStk n'est pas valide ", ErrorCode.MVT_STK_NOT_VALID, errors);
        }
        MvtStk mvtStk = this.mvtStkRepository.save(MvtStkMapping.toEntity(mvtStkDto));

        return MvtStkMapping.fromEntity(mvtStk);
    }

    @Override
    public MvtStkDto findById(Integer id) {
        if (null == id) {
            log.error("MvtStk ID is null");
            return null;
        }

        return this.mvtStkRepository.findById(id)
                .map(MvtStkMapping::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun mvtStk avec ID " + id + " n'a été trouvé dans la BDD", ErrorCode.MVT_STK_NOT_FOUND));
    }

    @Override
    public List<MvtStkDto> findAll() {
        List<MvtStk> mvtStkList = this.mvtStkRepository.findAll();
        log.error("Number mvtStk in BDD is {}", mvtStkList.size());
        return mvtStkList.stream().map(MvtStkMapping::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        MvtStkDto mvtStkDto = findById(id);
        if (null != mvtStkDto) this.mvtStkRepository.delete(MvtStkMapping.toEntity(mvtStkDto));
    }
}
