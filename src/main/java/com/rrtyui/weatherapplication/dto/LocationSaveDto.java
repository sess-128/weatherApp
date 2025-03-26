package com.rrtyui.weatherapplication.dto;

import java.math.BigDecimal;

public record LocationSaveDto(BigDecimal currentTemp,
                              BigDecimal feelsLike,
                              String city,
                              String country,
                              String skyState,
                              String humidity) {
}
