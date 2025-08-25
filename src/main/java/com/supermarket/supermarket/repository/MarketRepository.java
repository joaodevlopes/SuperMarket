package com.supermarket.supermarket.repository;

import com.supermarket.supermarket.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;

//mudar de class para interface e extender a jpa<nome da entidade, tipo do id>
public interface MarketRepository extends JpaRepository<Market, Long> {
}
