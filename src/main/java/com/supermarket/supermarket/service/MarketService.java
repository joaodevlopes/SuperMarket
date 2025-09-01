package com.supermarket.supermarket.service;
//logica do nosso negocio

import com.supermarket.supermarket.dto.MarketCreateDto;
import com.supermarket.supermarket.dto.MarketResponseDto;
import com.supermarket.supermarket.entity.MarketEntity;
import com.supermarket.supermarket.mapper.MarketMapper;
import com.supermarket.supermarket.repository.MarketRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    //Injetar o repository aqui
    private final MarketRepository marketRepository;
    private final MarketMapper marketMapper;
    public MarketService(MarketRepository marketRepository, MarketMapper marketMapper) {
        this.marketRepository = marketRepository;
        this.marketMapper = marketMapper;
    }
    //CREATE
    public MarketResponseDto create(MarketCreateDto dto){
        MarketEntity entity = marketMapper.MarketDtoToEntity(dto);
        MarketEntity saved = marketRepository.save(entity);
        return marketMapper.MarketEntitytoResponseDto(saved);
    }

    //LIST
    public List<MarketResponseDto> list(){
        Sort sort = Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.asc("date"),
                Sort.Order.desc("quantity")
        );
        return marketRepository.findAll(sort)
                .stream()
                .map(marketMapper::MarketEntitytoResponseDto) // transforma cada entity em responseDto
                .toList();// terminal: executa e retorna List (Java16+, imutável)
    }

    //UPDATE
    public MarketResponseDto update(Long id, MarketCreateDto dto){
        MarketEntity existing = marketRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existing.setName(dto.name());
        existing.setDescription(dto.description());
        existing.setPrice(dto.price());
        existing.setQuantity(dto.quantity());
        existing.setDate(dto.date());

        MarketEntity updated = marketRepository.save(existing);
        return marketMapper.MarketEntitytoResponseDto(updated);
    }

    //DELETE
    public void delete(Long id){
        MarketEntity entity = marketRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        marketRepository.delete(entity);
    }

    //SEARCHBYNAME
    public List<MarketResponseDto> searchByName(String name){
        return marketRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(marketMapper::MarketEntitytoResponseDto)
                .toList();
    }



}
