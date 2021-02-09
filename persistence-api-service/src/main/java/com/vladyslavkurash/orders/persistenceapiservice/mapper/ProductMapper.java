package com.vladyslavkurash.orders.persistenceapiservice.mapper;

import com.vladyslavkurash.orders.persistenceapiservice.domain.Product;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductResponseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

  ProductResponseDTO toDto(Product product);

  Product toEntity(ProductRequestDTO productRequestDTO);
}
