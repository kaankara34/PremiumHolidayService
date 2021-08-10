package com.aegon.premiumholiday.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PremiumHolidayRequest implements Serializable {
    private static final long serialVersionUID = -5883262811222969458L;
    private Long polid;
    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private Date islemTarihi;
}
