package com.vladyslavkurash.orders.persistenceapiservice.service;

import com.vladyslavkurash.orders.persistenceapiservice.domain.Order;
import com.vladyslavkurash.orders.persistenceapiservice.exceptions.OrderNotFoundException;
import com.vladyslavkurash.orders.persistenceapiservice.mapper.OrderMapper;
import com.vladyslavkurash.orders.persistenceapiservice.repository.OrderRepository;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderMapper orderMapper;

  @Autowired
  public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
    this.orderRepository = orderRepository;
    this.orderMapper = orderMapper;
  }

  public List<OrderResponseDTO> retrieveOrders() {
    return orderRepository.findAll()
        .stream()
        .map(orderMapper::toDto)
        .collect(Collectors.toList());
  }

  public OrderResponseDTO retrieveOrder(Long id) {
    return orderRepository.findById(id)
        .map(orderMapper::toDto)
        .orElseThrow(() -> new OrderNotFoundException(
            String.format("Order entity with id %s was not found", id)));
  }


  public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
    Order order = orderMapper.toEntity(orderRequestDTO);

    return orderMapper.toDto(orderRepository.save(order));
  }

  public void deleteOrder(Long id) {
    orderRepository.deleteById(id);
  }

  public Order retrieveOrderEntity(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> new OrderNotFoundException(
            String.format("Order entity with id %s not found", id)));
  }

}
