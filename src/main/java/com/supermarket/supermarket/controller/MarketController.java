package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.MarketDto;
import com.supermarket.supermarket.service.MarketService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    List<MarketDto> list(){
        return marketService.list();
    }
    @GetMapping("/search")
    public List<MarketDto> search(@RequestParam String name){
        return marketService.searchByName(name);
    }

    @PostMapping
    public void crete(@RequestBody MarketDto marketDto){
        marketService.create(marketDto);
    }


    @PutMapping
    public MarketDto update(@RequestBody MarketDto marketDto){
        return marketService.update(marketDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        marketService.delete(id);
        return ResponseEntity.ok().build();
    }
}
