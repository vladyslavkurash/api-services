package com.vladyslavkurash.orders.api.proxy;

import com.vladyslavkurash.orders.api.transfer.dto.OrderRequestDTO;
import com.vladyslavkurash.orders.api.transfer.dto.OrderResponseDTO;
import com.vladyslavkurash.orders.api.transfer.dto.ProductRequestDTO;
import com.vladyslavkurash.orders.api.transfer.dto.ProductResponseDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "persistence-api")
public interface PersistenceApiProxy {

  @GetMapping("/orders")
  List<OrderResponseDTO> retrieveOrders();

  @GetMapping("/orders/{orderId}")
  OrderResponseDTO retrieveOrder(@PathVariable("orderId") Long orderId);

  @PostMapping("/orders")
  OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO);

  @DeleteMapping("/orders/{orderId}")
  void deleteOrder(@PathVariable("orderId") Long orderId);

  @PostMapping("/orders/{orderId}/products")
  ProductResponseDTO createProduct(@PathVariable Long orderId,
      @RequestBody ProductRequestDTO productRequestDTO);

  @DeleteMapping("/orders/{orderId}/products/{productId}")
  void deleteProduct(@PathVariable("orderId") Long orderId, @PathVariable Long productId);

}