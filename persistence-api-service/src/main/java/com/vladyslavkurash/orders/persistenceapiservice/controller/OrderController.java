package com.vladyslavkurash.orders.persistenceapiservice.controller;

import com.vladyslavkurash.orders.persistenceapiservice.service.OrderService;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderResponseDTO;
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

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public ResponseEntity<List<OrderResponseDTO>> retrieveOrders() {
    return ResponseEntity.ok(orderService.retrieveOrders());
  }

  @GetMapping("{id}")
  public ResponseEntity<OrderResponseDTO> retrieveOrder(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.retrieveOrder(id));
  }

  @PostMapping
  public ResponseEntity<OrderResponseDTO> createOrder(
      @RequestBody OrderRequestDTO orderRequestDTO) {
    return ResponseEntity.ok(orderService.createOrder(orderRequestDTO));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
    orderService.deleteOrder(id);

    return ResponseEntity.ok().build();
  }

}
