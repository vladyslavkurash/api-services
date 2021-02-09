package com.vladyslavkurash.orders.persistenceapiservice.mapper;

import com.vladyslavkurash.orders.persistenceapiservice.domain.Order;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderResponseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(uses = {ProductMapper.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {

  OrderResponseDTO toDto(Order order);

  Order toEntity(OrderRequestDTO orderRequestDTO);
}
