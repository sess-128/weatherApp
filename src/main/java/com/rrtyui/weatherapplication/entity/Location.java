package com.rrtyui.weatherapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @Id
    private Long id;
    private String name;

    @OneToOne
    private User userId;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
