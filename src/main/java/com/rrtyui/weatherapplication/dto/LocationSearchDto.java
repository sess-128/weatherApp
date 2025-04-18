package com.rrtyui.weatherapplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LocationSearchDto {

    @JsonProperty("name")
    private String city;

    @JsonProperty("lon")
    private BigDecimal longitude;

    @JsonProperty("lat")
    private BigDecimal latitude;

    @JsonProperty("country")
    private String country;

    @JsonProperty("state")
    private String state;
}
