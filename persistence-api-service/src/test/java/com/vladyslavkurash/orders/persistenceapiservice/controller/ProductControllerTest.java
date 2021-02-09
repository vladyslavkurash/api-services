package com.vladyslavkurash.orders.persistenceapiservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vladyslavkurash.orders.persistenceapiservice.service.ProductService;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductResponseDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

  private final Long ORDER_ID = 1L;
  private final Long PRODUCT_ID = 5L;
  private final String PRODUCT_NAME = "Some name";
  private final Double PRODUCT_PRICE = 10.5;
  private final ProductRequestDTO productRequestDTO = generateProductRequestDTO();
  private final ProductResponseDTO productResponseDTO = generateProductResponseDTO();
  private final List<ProductResponseDTO> products = List.of(productResponseDTO);

  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

  @Test
  void retrieveProducts_ShouldCallServiceAndReturnProducts() {
    when(productService.retrieveProducts(ORDER_ID)).thenReturn(products);

    List<ProductResponseDTO> expected = products;
    List<ProductResponseDTO> actual = productController.retrieveProducts(ORDER_ID).getBody();

    assertEquals(expected, actual);
    verify(productService).retrieveProducts(ORDER_ID);
  }

  @Test
  void retrieveProduct_ShouldCallServiceAndReturnProduct() {
    when(productService.retrieveProduct(ORDER_ID, PRODUCT_ID)).thenReturn(productResponseDTO);

    ProductResponseDTO expected = productResponseDTO;
    ProductResponseDTO actual = productController.retrieveProduct(ORDER_ID, PRODUCT_ID).getBody();

    assertEquals(expected, actual);
    verify(productService).retrieveProduct(ORDER_ID, PRODUCT_ID);
  }

  @Test
  void createProduct_ShouldCallServiceAndReturnProduct() {
    when(productService.createProduct(ORDER_ID, productRequestDTO)).thenReturn(productResponseDTO);

    ProductResponseDTO expected = productResponseDTO;
    ProductResponseDTO actual = productController.createProduct(ORDER_ID, productRequestDTO)
        .getBody();

    assertEquals(expected, actual);
    verify(productService).createProduct(ORDER_ID, productRequestDTO);
  }

  @Test
  void deleteProduct_ShouldCallServiceAndReturnNothing() {
    productController.deleteProduct(ORDER_ID, PRODUCT_ID);

    verify(productService).deleteProduct(ORDER_ID, PRODUCT_ID);
  }

  private ProductRequestDTO generateProductRequestDTO() {
    return new ProductRequestDTO(PRODUCT_NAME, PRODUCT_PRICE);
  }

  private ProductResponseDTO generateProductResponseDTO() {
    return new ProductResponseDTO(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE);
  }

}
