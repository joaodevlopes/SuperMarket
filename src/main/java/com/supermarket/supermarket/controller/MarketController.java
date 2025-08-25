package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.entity.Market;
import com.supermarket.supermarket.service.MarketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    //injetar a repository
    private MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @PostMapping
    List<Market> create(@RequestBody @Valid Market market) {
        return marketService.create(market);
    }

    @GetMapping
    List<Market> list(){
        return marketService.list();
    }

    @PutMapping
    List<Market> update(@RequestBody Market market){
        return marketService.update(market);
    }

    @DeleteMapping("{id}")
    List<Market> delete(@PathVariable("id") Long id){
        return marketService.delete(id);

    }
}
