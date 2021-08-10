package com.aegon.premiumholiday.repository;

import java.util.Date;
import java.util.Map;

public interface MkHesaplaCustomRepository {
    Map<String, Object> getMkValues(Long polid, Date tarih);

}
