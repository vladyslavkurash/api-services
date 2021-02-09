package com.vladyslavkurash.orders.api.controller;

import static org.mockito.Mockito.verify;

import com.vladyslavkurash.orders.api.proxy.PersistenceApiProxy;
import com.vladyslavkurash.orders.api.transfer.dto.ProductRequestDTO;
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

  @Mock
  private PersistenceApiProxy persistenceApiProxy;

  @InjectMocks
  private ProductController productController;

  @Test
  void createProduct_ShouldCallProxyCreateProduct() {
    productController.createProduct(ORDER_ID, productRequestDTO);

    verify(persistenceApiProxy).createProduct(ORDER_ID, productRequestDTO);
  }

  @Test
  void deleteProduct_ShouldCallProxyDeleteProduct() {
    productController.deleteProduct(ORDER_ID, PRODUCT_ID);

    verify(persistenceApiProxy).deleteProduct(ORDER_ID, PRODUCT_ID);
  }

  private ProductRequestDTO generateProductRequestDTO() {
    return new ProductRequestDTO(PRODUCT_NAME, PRODUCT_PRICE);
  }

}
