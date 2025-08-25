package com.supermarket.supermarket.service;
//logica do nosso negocio

import com.supermarket.supermarket.entity.Market;
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
        marketRepository.save(market);
        return list();
    }
    public List<Market> delete(Long id){
        marketRepository.deleteById(id);
        return list();
    }
}
