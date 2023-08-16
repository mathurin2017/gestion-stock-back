package com.lloufa.gestionstockback.dto;

import com.lloufa.gestionstockback.model.TypeMvt;
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

    private TypeMvt typeMvt;

    private ArticleDto articleDto;

}
