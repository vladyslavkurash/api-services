package com.vladyslavkurash.orders.persistenceapiservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.qos.logback.core.net.ObjectWriter;
import com.vladyslavkurash.orders.persistenceapiservice.domain.Order;
import com.vladyslavkurash.orders.persistenceapiservice.exceptions.OrderNotFoundException;
import com.vladyslavkurash.orders.persistenceapiservice.mapper.OrderMapper;
import com.vladyslavkurash.orders.persistenceapiservice.repository.OrderRepository;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderResponseDTO;
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
class OrderServiceTest {

  private final Long CORRECT_ID = 1L;
  private final Long WRONG_ID = 2L;
  private final String ORDER_OWNER = "Some owner";

  private final Order order = generateEntity();
  private final OrderRequestDTO orderRequestDTO = generateOrderRequestDTO();
  private final OrderResponseDTO orderResponseDTO = generateOrderResponseDTO();

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private OrderMapper orderMapper;

  @InjectMocks
  private OrderService orderService;

  @Test
  void retrieveOrders_ShouldReturnResponseDTOList() {
    List<Order> orderList = new ArrayList<>(Collections.singletonList(order));
    when(orderRepository.findAll()).thenReturn(orderList);
    when(orderMapper.toDto(order)).thenReturn(orderResponseDTO);

    List<OrderResponseDTO> expected = new ArrayList<>(Collections.singletonList(orderResponseDTO));
    List<OrderResponseDTO> actual = orderService.retrieveOrders();

    assertEquals(expected, actual);
  }

  @Test
  void retrieveOrder_ShouldReturnOrderResponseDTO() {
    when(orderRepository.findById(CORRECT_ID)).thenReturn(Optional.of(order));
    when(orderMapper.toDto(order)).thenReturn(orderResponseDTO);

    OrderResponseDTO expected = orderResponseDTO;
    OrderResponseDTO actual = orderService.retrieveOrder(CORRECT_ID);

    assertEquals(expected, actual);
  }

  @Test
  void retrieveOrder_ShouldThrowOrderNotFoundException() {
    when(orderRepository.findById(WRONG_ID)).thenReturn(Optional.empty());

    assertThrows(OrderNotFoundException.class, () -> orderService.retrieveOrder(WRONG_ID));
  }

  @Test
  void createOrder_ShouldSaveAndReturnDTO() {
    when(orderRepository.save(order)).thenReturn(order);
    when(orderMapper.toEntity(orderRequestDTO)).thenReturn(order);
    when(orderMapper.toDto(order)).thenReturn(orderResponseDTO);

    OrderResponseDTO expected = orderResponseDTO;
    OrderResponseDTO actual = orderService.createOrder(orderRequestDTO);

    verify(orderRepository).save(order);
    assertEquals(expected, actual);
  }

  @Test
  void deleteOrder_ShouldDeleteAndReturnNothing() {
    orderService.deleteOrder(CORRECT_ID);

    verify(orderRepository).deleteById(CORRECT_ID);
  }

  @Test
  void retrieveOrderEntity() {
    when(orderRepository.findById(CORRECT_ID)).thenReturn(Optional.of(order));

    Order expected = order;
    Order actual = orderService.retrieveOrderEntity(CORRECT_ID);

    assertEquals(expected, actual);
  }

  private Order generateEntity() {
    Order order = new Order();
    order.setId(CORRECT_ID);
    order.setOwner(ORDER_OWNER);
    order.setProducts(List.of());
    return order;
  }

  private OrderRequestDTO generateOrderRequestDTO() {
    return new OrderRequestDTO(ORDER_OWNER);
  }

  private OrderResponseDTO generateOrderResponseDTO() {
    return new OrderResponseDTO(CORRECT_ID, ORDER_OWNER, List.of());
  }

}