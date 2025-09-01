package com.supermarket.supermarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supermarket.supermarket.dto.MarketCreateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "market")
public class MarketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    @Min(value = 0)
    private Integer quantity;
    @NotNull
    private LocalDate date;

    //converter DTO para entity
    public MarketEntity(MarketCreateDto marketCreateDto) {
        BeanUtils.copyProperties(marketCreateDto, this);
    }
    public MarketEntity() {
    }
    public MarketEntity(Long id, String name, String description, double price, int quantity, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
