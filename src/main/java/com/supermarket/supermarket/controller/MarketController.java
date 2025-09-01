package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.MarketCreateDto;
import com.supermarket.supermarket.dto.MarketResponseDto;
import com.supermarket.supermarket.service.MarketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {
    //injetar o service
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping
    public List<MarketResponseDto> list(){
        return marketService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MarketResponseDto create(@Valid @RequestBody MarketCreateDto dto){
        return marketService.create(dto);
    }


    @PutMapping("/{id}")
    public MarketResponseDto update(@PathVariable Long id, @Valid @RequestBody MarketCreateDto dto ){
        return marketService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        marketService.delete(id);
    }
    @GetMapping("/search")
    public List<MarketResponseDto> search(@RequestParam String name){
        return marketService.searchByName(name);
    }



}
