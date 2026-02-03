package com.example.day3springboot.dto;

public record StudentResponseDto(
        String id,
        String name,
        int age,
        String email
) {
}
