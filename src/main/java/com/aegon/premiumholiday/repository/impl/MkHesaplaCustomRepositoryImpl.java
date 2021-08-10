package com.aegon.premiumholiday.repository.impl;

import com.aegon.premiumholiday.repository.MkHesaplaCustomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MkHesaplaCustomRepositoryImpl implements MkHesaplaCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Value(value = "${phapp.mkhesapla.script.procedure}")
    private String PHAPP_MKHESAPLA_PROCEDURE;
    @Value(value = "${phapp.mkhesapla.script.procedure.calctype}")
    private String CALCTYPE;
    @Value(value = "${phapp.mkhesapla.script.procedure.polid}")
    private String POLID;
    @Value(value = "${phapp.mkhesapla.script.procedure.islemtar}")
    private String ISLEMTAR;
    @Value(value = "${phapp.mkhesapla.script.procedure.islemsonucu}")
    private String ISLEMSONUCU;
    @Value(value = "${phapp.mkhesapla.script.procedure.donemselmkLocal}")
    private String DONEMSELMK_LOCAL;
    @Value(value = "${phapp.mkhesapla.script.procedure.lokalmkLocal}")
    private String LOKALMK_LOCAL;
    @Value(value = "${phapp.mkhesapla.script.procedure.donemselmkIfrs}")
    private String DONEMSELMK_IFRS;
    @Value(value = "${phapp.mkhesapla.script.procedure.lokalmkIfrs}")
    private String LOKALMK_IFRS;

    @Override
    public Map<String, Object> getMkValues(Long polid, Date tarih) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery(PHAPP_MKHESAPLA_PROCEDURE);
        query.registerStoredProcedureParameter(
                CALCTYPE,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                POLID,
                Long.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                ISLEMTAR,
                Date.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                ISLEMSONUCU,
                String.class,
                ParameterMode.OUT
        );
        query.registerStoredProcedureParameter(
                DONEMSELMK_LOCAL,
                Integer.class,
                ParameterMode.OUT
        );
        query.registerStoredProcedureParameter(
                LOKALMK_LOCAL,
                Integer.class,
                ParameterMode.OUT
        );
        query.registerStoredProcedureParameter(
                DONEMSELMK_IFRS,
                Integer.class,
                ParameterMode.OUT
        );
        query.registerStoredProcedureParameter(
                LOKALMK_IFRS,
                Integer.class,
                ParameterMode.OUT
        );

        query.setParameter(POLID, polid);
        query.setParameter(ISLEMTAR, tarih);
        query.setParameter(CALCTYPE, "W");
        query.execute();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("islemsonucu", query.getOutputParameterValue(ISLEMSONUCU));
        resultMap.put("donemselmk", query.getOutputParameterValue(DONEMSELMK_LOCAL));
        resultMap.put("lokalmk", query.getOutputParameterValue(LOKALMK_LOCAL));
        resultMap.put("donemselmkIfrs", query.getOutputParameterValue(DONEMSELMK_IFRS));
        resultMap.put("lokalmkIfrs", query.getOutputParameterValue(LOKALMK_IFRS));
        return resultMap;
    }
}
