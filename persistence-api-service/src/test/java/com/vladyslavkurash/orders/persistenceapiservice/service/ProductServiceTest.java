package com.vladyslavkurash.orders.persistenceapiservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vladyslavkurash.orders.persistenceapiservice.domain.Order;
import com.vladyslavkurash.orders.persistenceapiservice.domain.Product;
import com.vladyslavkurash.orders.persistenceapiservice.exceptions.ProductNotFoundException;
import com.vladyslavkurash.orders.persistenceapiservice.mapper.ProductMapper;
import com.vladyslavkurash.orders.persistenceapiservice.repository.ProductRepository;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductResponseDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  private final Long CORRECT_ID = 3L;
  private final Long WRONG_ID = 4L;

  private final String PRODUCT_NAME = "Some name";
  private final Double PRODUCT_PRICE = 10.5;

  private final Long ORDER_ID = 1L;
  private final String ORDER_OWNER = "Some owner";

  private final Order order = generateOrderEntity();

  private final Product product = generateProductEntity();
  private final ProductRequestDTO productRequestDTO = generateProductRequestDTO();
  private final ProductResponseDTO productResponseDTO = generateProductResponseDTO();

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductMapper productMapper;

  @Mock
  private OrderService orderService;

  @InjectMocks
  private ProductService productService;

  @Test
  void retrieveProducts_ShouldReturnResponseDTOList() {
    List<Product> productList = new ArrayList<>(Collections.singletonList(product));
    when(productRepository.findAllByOrder_Id(ORDER_ID)).thenReturn(productList);
    when(productMapper.toDto(product)).thenReturn(productResponseDTO);

    List<ProductResponseDTO> expected = new ArrayList<>(
        Collections.singletonList(productResponseDTO));
    List<ProductResponseDTO> actual = productService.retrieveProducts(ORDER_ID);

    assertEquals(expected, actual);
  }

  @Test
  void retrieveProduct_ShouldReturnProductResponseDTO() {
    when(productRepository.findByOrder_IdAndId(ORDER_ID, CORRECT_ID))
        .thenReturn(Optional.of(product));
    when(productMapper.toDto(product)).thenReturn(productResponseDTO);

    ProductResponseDTO expected = productResponseDTO;
    ProductResponseDTO actual = productService.retrieveProduct(ORDER_ID, CORRECT_ID);

    assertEquals(expected, actual);
  }

  @Test
  void retrieveProduct_ShouldThrowProductNotFoundException() {
    when(productRepository.findByOrder_IdAndId(ORDER_ID, WRONG_ID)).thenReturn(Optional.empty());

    assertThrows(ProductNotFoundException.class,
        () -> productService.retrieveProduct(ORDER_ID, WRONG_ID));
  }

  @Test
  void createProduct_ShouldSaveAndReturnDTO() {
    when(productRepository.save(product)).thenReturn(product);
    when(productMapper.toEntity(productRequestDTO)).thenReturn(product);
    when(orderService.retrieveOrderEntity(ORDER_ID)).thenReturn(order);
    when(productMapper.toDto(product)).thenReturn(productResponseDTO);

    ProductResponseDTO expected = productResponseDTO;
    ProductResponseDTO actual = productService.createProduct(ORDER_ID, productRequestDTO);

    verify(productRepository).save(product);
    assertEquals(expected, actual);
  }

  @Test
  void deleteProduct_ShouldDeleteAndReturnNothing() {
    productService.deleteProduct(ORDER_ID, CORRECT_ID);

    verify(productRepository).deleteByOrder_IdAndId(ORDER_ID, CORRECT_ID);
  }

  private Order generateOrderEntity() {
    Order order = new Order();
    order.setId(1L);
    order.setOwner(ORDER_OWNER);
    order.setProducts(List.of());
    return order;
  }

  private Product generateProductEntity() {
    Product product = new Product();
    product.setId(1L);
    product.setName(PRODUCT_NAME);
    product.setPrice(PRODUCT_PRICE);
    return product;
  }

  private ProductRequestDTO generateProductRequestDTO() {
    return new ProductRequestDTO(PRODUCT_NAME, PRODUCT_PRICE);
  }

  private ProductResponseDTO generateProductResponseDTO() {
    return new ProductResponseDTO(CORRECT_ID, PRODUCT_NAME, PRODUCT_PRICE);
  }

}
