package com.rrtyui.weatherapplication.dto;

public record UserResDto(Long id,
                         String login,
                         String hashedPassword) {
}
