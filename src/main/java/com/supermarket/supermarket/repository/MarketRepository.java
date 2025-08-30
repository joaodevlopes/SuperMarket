package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//mudar de class para interface e extender a jpa<nome da entidade, tipo do id>
public interface MarketRepository extends JpaRepository<MarketEntity, Long> {
    List<MarketEntity> findByNameContainingIgnoreCase(String name);
}
