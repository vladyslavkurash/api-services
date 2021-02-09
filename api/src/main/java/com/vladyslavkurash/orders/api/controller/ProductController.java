package com.vladyslavkurash.orders.api.controller;

import com.vladyslavkurash.orders.api.proxy.PersistenceApiProxy;
import com.vladyslavkurash.orders.api.transfer.dto.ProductRequestDTO;
import com.vladyslavkurash.orders.api.transfer.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  private final PersistenceApiProxy persistenceApiProxy;

  @Autowired
  public ProductController(PersistenceApiProxy persistenceApiProxy) {
    this.persistenceApiProxy = persistenceApiProxy;
  }

  @PostMapping("orders/{orderId}/products")
  public ResponseEntity<ProductResponseDTO> createProduct(@PathVariable Long orderId,
      @RequestBody ProductRequestDTO productRequestDTO) {
    return ResponseEntity.ok(persistenceApiProxy.createProduct(orderId, productRequestDTO));
  }

  @DeleteMapping("orders/{orderId}/products/{productId}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long orderId,
      @PathVariable Long productId) {
    persistenceApiProxy.deleteProduct(orderId, productId);

    return ResponseEntity.ok().build();
  }

}
