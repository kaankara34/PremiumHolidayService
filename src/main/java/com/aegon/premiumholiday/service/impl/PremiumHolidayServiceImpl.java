package com.aegon.premiumholiday.service.impl;

import com.aegon.premiumholiday.model.PremiumHolidayRequest;
import com.aegon.premiumholiday.model.PremiumHolidayResponse;
import com.aegon.premiumholiday.repository.MkHesaplaCustomRepository;
import com.aegon.premiumholiday.repository.WriteMkCalclogCustomRepository;
import com.aegon.premiumholiday.service.PremiumHolidayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class PremiumHolidayServiceImpl implements PremiumHolidayService {

    private final MkHesaplaCustomRepository premiumHolidayCustomRepository;
    private final WriteMkCalclogCustomRepository writeMkCalclogCustomRepository;
    private EntityManager entityManager;

    @Override
    public PremiumHolidayResponse getMkResults(PremiumHolidayRequest premiumHolidayRequest) {
        PremiumHolidayResponse premiumHolidayResponse = new PremiumHolidayResponse();


        try {
            Query nativeQuery = entityManager.createNativeQuery("select PHAPP.CALCIDSEQ.nextval from dual");
            List<BigDecimal> indexList = nativeQuery.getResultList();
            Long index = Long.parseLong(indexList.stream().findFirst().orElse(null).toString());
            Map<String, Object> mkValues = premiumHolidayCustomRepository.getMkValues(premiumHolidayRequest.getPolid(), premiumHolidayRequest.getIslemTarihi());
            premiumHolidayResponse.setIslemSonucu(convertObjToString(mkValues.get("islemsonucu")));
            premiumHolidayResponse.setDonemselMk(convertObjToString(mkValues.get("donemselmk")));
            premiumHolidayResponse.setLokalMk(convertObjToString(mkValues.get("lokalmk")));

            writeMkCalclogCustomRepository.writeMkCalclog(index, premiumHolidayRequest.getPolid(), "BESPROD", "W",
                    premiumHolidayRequest.getIslemTarihi(),
                    convertObjToString(mkValues.get("islemsonucu")), convertObjToString(mkValues.get("donemselmk")), convertObjToString(mkValues.get("lokalmk")),
                    convertObjToString(mkValues.get("donemselmkIfrs")), convertObjToString(mkValues.get("lokalmkIfrs")));


        } catch (Exception ex) {
            premiumHolidayResponse.setIslemSonucu("Hata:" + ex.getMessage());
            return premiumHolidayResponse;
        }

        return premiumHolidayResponse;
    }

    private String convertObjToString(Object obj) {
        if (StringUtils.isEmpty(obj)) {
            return null;
        } else {
            return obj.toString();
        }
    }
}
