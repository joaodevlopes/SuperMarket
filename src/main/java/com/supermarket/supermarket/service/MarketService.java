package com.supermarket.supermarket.service;
//logica do nosso negocio

import com.supermarket.supermarket.dto.MarketDto;
import com.supermarket.supermarket.entity.MarketEntity;
import com.supermarket.supermarket.repository.MarketRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketService {

    //Injetar o repository aqui
    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    //metodo para conversão para DTO:
    private MarketDto toDTO(MarketEntity market){
        return new MarketDto(
                market.getId(),
                market.getName(),
                market.getDescription(),
                market.getPrice(),
                market.getQuantity(),
                market.getDate()
        );
    }

    //METODO PRA CRIAR
    public MarketDto create(MarketDto marketDto){
        MarketEntity marketEntity = new MarketEntity(marketDto);
        return new MarketDto(marketRepository.save(marketEntity));
    }

    //LISTAR TODOS
    public List<MarketDto> list(){
        Sort sort = Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.asc("date"),
                Sort.Order.desc("quantity")
        );
       return marketRepository.findAll(sort)
               .stream()
               .map(this::toDTO)
               .collect(Collectors.toList());
    }

    //ATUALIZAR DADOS
    public MarketDto update(MarketDto marketDto){
        MarketEntity marketEntity = new MarketEntity(marketDto);
        return new MarketDto(marketRepository.save(marketEntity));
    }

    //DELETAR DADOS
    public void delete(Long id){
        MarketEntity marketEntity = marketRepository.findById(id).orElseThrow(() -> new RuntimeException(("Produto não encontrado!")));
        marketRepository.delete(marketEntity);
    }

    //BUSCAR POR NOME
    public List<MarketDto> searchByName(String name){
        return marketRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
