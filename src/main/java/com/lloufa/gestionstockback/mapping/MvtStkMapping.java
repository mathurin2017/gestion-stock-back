package com.lloufa.gestionstockback.mapping;

import com.lloufa.gestionstockback.dto.MvtStkDto;
import com.lloufa.gestionstockback.model.MvtStk;

public class MvtStkMapping {

    public static MvtStkDto fromEntity(MvtStk mvtStk) {
        if (null == mvtStk) {
            return null;
            // TODO throw an execption
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .dateMvt(mvtStk.getDateMvt())
                .quantite(mvtStk.getQuantite())
                .articleDto(ArticleMapping.fromEntity(mvtStk.getArticle()))
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto) {
        if (null == mvtStkDto) {
            return null;
            // TODO throw an execption
        }

        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDateMvt(mvtStkDto.getDateMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setArticle(ArticleMapping.toEntity(mvtStkDto.getArticleDto()));

        return mvtStk;
    }
}
