package com.supermarket.supermarket.service;
//logica do nosso negocio

import com.supermarket.supermarket.entity.Market;
import com.supermarket.supermarket.exceptions.ResourceNotFoundException;
import com.supermarket.supermarket.repository.MarketRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    //Injetar o repository aqui
    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public List<Market> create(Market market){
        marketRepository.save(market);
        return list();
    }

    public List<Market> list(){
        Sort sort = Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.asc("date"),
                Sort.Order.desc("quantity")
        );
        return marketRepository.findAll(sort);

    }

    public List<Market> update(Market market){
        if(market.getId() == null || !marketRepository.existsById(market.getId())){
            throw new ResourceNotFoundException("Produto com o id: " + market.getId() + " não encontrado para atualização.");
        }

        marketRepository.save(market);
        return list();
    }

    public List<Market> delete(Long id){
        if(!marketRepository.existsById(id)){
            throw new ResourceNotFoundException("Produto com o id: " + id + " não encontrado.");
        }
        marketRepository.deleteById(id);
        return list();
    }
}
