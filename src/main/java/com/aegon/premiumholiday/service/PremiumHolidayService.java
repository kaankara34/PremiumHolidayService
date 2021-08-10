package com.aegon.premiumholiday.service;

import com.aegon.premiumholiday.model.PremiumHolidayRequest;
import com.aegon.premiumholiday.model.PremiumHolidayResponse;

public interface PremiumHolidayService {
    PremiumHolidayResponse getMkResults(PremiumHolidayRequest premiumHolidayRequest);
}
