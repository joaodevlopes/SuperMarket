package com.supermarket.supermarket.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record MarketCreateDto(
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @Positive Double price,
        @NotNull @Min(0) Integer quantity,
        @NotNull LocalDate date
){}





