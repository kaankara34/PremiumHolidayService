package com.aegon.premiumholiday.controller;

import com.aegon.premiumholiday.model.PremiumHolidayRequest;
import com.aegon.premiumholiday.model.PremiumHolidayResponse;
import com.aegon.premiumholiday.service.PremiumHolidayService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
public class PremiumHolidayController {

    private final PremiumHolidayService premiumHolidayService;


    @PostMapping("PremiumHoliday/api/v1/MKHesapla")
    public ResponseEntity<PremiumHolidayResponse> getThresholdValue(@RequestBody PremiumHolidayRequest premiumHolidayRequest) throws Exception {

        PremiumHolidayResponse response = premiumHolidayService.getMkResults(premiumHolidayRequest);
        return ResponseEntity.ok(response);
    }


}
