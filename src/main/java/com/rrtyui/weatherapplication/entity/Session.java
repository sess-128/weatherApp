package com.rrtyui.weatherapplication.entity;

import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private UUID id;
    private Long userId;
    private LocalTime expiresAt;
}
