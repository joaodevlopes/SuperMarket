package com.supermarket.supermarket.dto;

import java.time.LocalDate;

public record MarketResponseDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer quantity,
        LocalDate date
){}
