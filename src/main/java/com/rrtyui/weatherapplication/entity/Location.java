package com.rrtyui.weatherapplication.entity;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;
    private String name;
    private Long userId;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
