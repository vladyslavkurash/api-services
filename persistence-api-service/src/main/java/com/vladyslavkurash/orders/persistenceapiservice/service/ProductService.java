package com.vladyslavkurash.orders.persistenceapiservice.service;

import com.vladyslavkurash.orders.persistenceapiservice.domain.Order;
import com.vladyslavkurash.orders.persistenceapiservice.domain.Product;
import com.vladyslavkurash.orders.persistenceapiservice.exceptions.OrderNotFoundException;
import com.vladyslavkurash.orders.persistenceapiservice.exceptions.ProductNotFoundException;
import com.vladyslavkurash.orders.persistenceapiservice.mapper.ProductMapper;
import com.vladyslavkurash.orders.persistenceapiservice.repository.ProductRepository;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final OrderService orderService;
  private final ProductMapper productMapper;

  @Autowired
  public ProductService(ProductRepository productRepository, OrderService orderService,
      ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.orderService = orderService;
    this.productMapper = productMapper;
  }

  public List<ProductResponseDTO> retrieveProducts(Long orderId) {
    return productRepository.findAllByOrder_Id(orderId)
        .stream()
        .map(productMapper::toDto)
        .collect(Collectors.toList());
  }

  public ProductResponseDTO retrieveProduct(Long orderId, Long productId) {
    return productRepository.findByOrder_IdAndId(orderId, productId)
        .map(productMapper::toDto)
        .orElseThrow(() -> new ProductNotFoundException(String.format(
            "Product entity with id %s and order id %s was not found", orderId, productId)));
  }

  public ProductResponseDTO createProduct(Long orderId, ProductRequestDTO productRequestDTO) {
    Product product = productMapper.toEntity(productRequestDTO);
    product.setOrder(retrieveOrderEntity(orderId));

    return productMapper.toDto(productRepository.save(product));
  }

  @Transactional
  public void deleteProduct(Long orderId, Long productId) {
    productRepository.deleteByOrder_IdAndId(orderId, productId);
  }

  private Order retrieveOrderEntity(Long id) {
    return orderService.retrieveOrderEntity(id);
  }

}
