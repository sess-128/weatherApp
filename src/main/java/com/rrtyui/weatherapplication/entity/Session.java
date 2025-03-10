package com.rrtyui.weatherapplication.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
public class Session {

    @Id
    private UUID id;
    private Long userId;
    private LocalTime expiresAt;
}
