package com.aegon.premiumholiday.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PremiumHolidayResponse implements Serializable {
    private static final long serialVersionUID = -588326281122332358L;
    private String islemSonucu;
    private String donemselMk;
    private String lokalMk;
}
