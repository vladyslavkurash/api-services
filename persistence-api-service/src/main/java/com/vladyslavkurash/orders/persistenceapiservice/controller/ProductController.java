package com.vladyslavkurash.orders.persistenceapiservice.controller;

import com.vladyslavkurash.orders.persistenceapiservice.service.ProductService;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductResponseDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("orders/{orderId}/products")
  public ResponseEntity<List<ProductResponseDTO>> retrieveProducts(@PathVariable Long orderId) {
    return ResponseEntity.ok(productService.retrieveProducts(orderId));
  }

  @GetMapping("orders/{orderId}/products/{productId}")
  @Cacheable(cacheNames = "products", key = "#productId")
  public ResponseEntity<ProductResponseDTO> retrieveProduct(@PathVariable Long orderId,
      @PathVariable Long productId) {
    return ResponseEntity.ok(productService.retrieveProduct(orderId, productId));
  }

  @PostMapping("orders/{orderId}/products")
  public ResponseEntity<ProductResponseDTO> createProduct(@PathVariable Long orderId,
      @RequestBody ProductRequestDTO productRequestDTO) {
    return ResponseEntity.ok(productService.createProduct(orderId, productRequestDTO));
  }

  @DeleteMapping("orders/{orderId}/products/{productId}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long orderId,
      @PathVariable Long productId) {
    productService.deleteProduct(orderId, productId);

    return ResponseEntity.ok().build();
  }

}
