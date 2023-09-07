package com.lloufa.gestionstockback.dto;

import com.lloufa.gestionstockback.model.SourceMvtStk;
import com.lloufa.gestionstockback.model.TypeMvtStk;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;
    private Instant dateMvt;
    private BigDecimal quantite;
    private TypeMvtStk typeMvtStk;
    private SourceMvtStk sourceMvtStk;
    private String idEntreprise;
    private ArticleDto articleDto;

}
