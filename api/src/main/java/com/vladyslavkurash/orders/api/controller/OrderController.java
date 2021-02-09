package com.vladyslavkurash.orders.api.controller;

import com.vladyslavkurash.orders.api.proxy.PersistenceApiProxy;
import com.vladyslavkurash.orders.api.transfer.dto.OrderRequestDTO;
import com.vladyslavkurash.orders.api.transfer.dto.OrderResponseDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("orders")
public class OrderController {

  private final PersistenceApiProxy persistenceApiProxy;

  @Autowired
  public OrderController(PersistenceApiProxy persistenceApiProxy) {
    this.persistenceApiProxy = persistenceApiProxy;
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDTO>> retrieveOrders() {
    return ResponseEntity.ok(persistenceApiProxy.retrieveOrders());
  }

  @GetMapping("{orderId}")
  public ResponseEntity<OrderResponseDTO> retrieveOrder(@PathVariable Long orderId) {
    return ResponseEntity.ok(persistenceApiProxy.retrieveOrder(orderId));
  }

  @PostMapping
  public ResponseEntity<OrderResponseDTO> createOrder(
      @RequestBody OrderRequestDTO orderRequestDTO) {
    return ResponseEntity.ok(persistenceApiProxy.createOrder(orderRequestDTO));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
    persistenceApiProxy.deleteOrder(id);

    return ResponseEntity.ok().build();
  }
}
