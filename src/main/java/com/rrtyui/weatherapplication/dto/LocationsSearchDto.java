package com.rrtyui.weatherapplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class LocationsSearchDto {
    private List<LocationSearchDto> locations;
}
