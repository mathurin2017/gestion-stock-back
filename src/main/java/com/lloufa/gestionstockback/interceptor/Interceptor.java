package com.lloufa.gestionstockback.interceptor;

import com.lloufa.gestionstockback.Utils.ConstantEnumUtils;
import org.hibernate.EmptyInterceptor;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        if (StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
            // select utilisateu0_.
            final String entityName = sql.substring(7, sql.indexOf("."));
            final String idEntreprise = MDC.get(ConstantEnumUtils.ID_ENTREPRISE.getValue());

            if (StringUtils.hasLength(entityName)
                    && !entityName.toLowerCase().contains("entreprise")
                    && !entityName.toLowerCase().contains("roles")
                    && StringUtils.hasLength(idEntreprise)
            ) {
                if (sql.contains("where")) sql += " and " + entityName + "." + ConstantEnumUtils.ID_ENTREPRISE.getValue().toLowerCase() + " = " + idEntreprise;
                else sql += " where " + entityName + "." + ConstantEnumUtils.ID_ENTREPRISE.getValue().toLowerCase() + " = " + idEntreprise;
            }
        }

        return super.onPrepareStatement(sql);
    }

}
