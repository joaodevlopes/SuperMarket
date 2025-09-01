package com.supermarket.supermarket.mapper;

import com.supermarket.supermarket.dto.MarketCreateDto;
import com.supermarket.supermarket.dto.MarketResponseDto;
import com.supermarket.supermarket.entity.MarketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring") // gera um bean Spring
public interface MarketMapper {

    MarketEntity MarketDtoToEntity(MarketCreateDto createDto);
    //Entity -> ResponseDto (usado no Get/ retorno para o cliente)
    MarketResponseDto MarketEntitytoResponseDto(MarketEntity entity);
}
