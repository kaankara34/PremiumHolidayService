package com.aegon.premiumholiday.repository.impl;

import com.aegon.premiumholiday.repository.WriteMkCalclogCustomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;

@Repository
public class WriteMkCalclogCustomRepositoryImpl implements WriteMkCalclogCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Value(value = "${phapp.writeMkcalclog.script.procedure}")
    private String PHAPP_WRITE_MKCALCLOG_PROCEDURE;
    @Value(value = "${phapp.writeMkcalclog.script.procedure.pseq}")
    private String PSEQ;
    @Value(value = "${phapp.writeMkcalclog.script.procedure.ppolid}")
    private String PPOLID;
    @Value(value = "${phapp.writeMkcalclog.script.procedure.puser}")
    private String PUSER;
    @Value(value = "${phapp.writeMkcalclog.script.procedure.plogtype}")
    private String PLOGTYPE;
    @Value(value = "${phapp.writeMkcalclog.script.procedure.pcalcdate}")
    private String PCALCDATE;
    @Value(value = "${phapp.writeMkcalclog.script.procedure.pcalcstatus}")
    private String PCALCSTATUS;
    @Value(value = "${phapp.mkhesapla.script.procedure.donemselmkLocal}")
    private String DONEMSELMK_LOCAL;
    @Value(value = "${phapp.mkhesapla.script.procedure.lokalmkLocal}")
    private String LOKALMK_LOCAL;
    @Value(value = "${phapp.mkhesapla.script.procedure.donemselmkIfrs}")
    private String DONEMSELMK_IFRS;
    @Value(value = "${phapp.mkhesapla.script.procedure.lokalmkIfrs}")
    private String LOKALMK_IFRS;

    @Override
    public void writeMkCalclog(Long pseq, Long ppolid, String puser, String plogtype, Date pcalcdate, String pcalcstatus, String donemselmkLocal,
                               String lokalmkLocal, String donemselmkIfrs, String lokalmkIfrs) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery(PHAPP_WRITE_MKCALCLOG_PROCEDURE);
        query.registerStoredProcedureParameter(
                PSEQ,
                Long.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                PPOLID,
                Long.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                PUSER,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                PLOGTYPE,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                PCALCDATE,
                Date.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                PCALCSTATUS,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                DONEMSELMK_LOCAL,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                LOKALMK_LOCAL,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                DONEMSELMK_IFRS,
                String.class,
                ParameterMode.IN
        );
        query.registerStoredProcedureParameter(
                LOKALMK_IFRS,
                String.class,
                ParameterMode.IN
        );


        query.setParameter(PSEQ, pseq);
        query.setParameter(PPOLID, ppolid);
        query.setParameter(PUSER, puser);
        query.setParameter(PLOGTYPE, plogtype);
        query.setParameter(PCALCDATE, pcalcdate);
        query.setParameter(PCALCSTATUS, pcalcstatus);
        query.setParameter(DONEMSELMK_LOCAL, donemselmkLocal);
        query.setParameter(LOKALMK_LOCAL, lokalmkLocal);
        query.setParameter(DONEMSELMK_IFRS, donemselmkIfrs);
        query.setParameter(LOKALMK_IFRS, lokalmkIfrs);
        query.execute();
    }
}
