package com.rrtyui.weatherapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    private UUID id;

    @OneToOne
    private User userId;

    @Column(name = "expires_at")
    private LocalTime expiresAt;
}
