package com.vladyslavkurash.orders.api.controller;

import static org.mockito.Mockito.verify;

import com.vladyslavkurash.orders.api.proxy.PersistenceApiProxy;
import com.vladyslavkurash.orders.api.transfer.dto.OrderRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

  private final Long ORDER_ID = 1L;
  private final String ORDER_OWNER = "Some owner";
  private final OrderRequestDTO orderRequestDTO = generateOrderRequestDTO();

  @Mock
  private PersistenceApiProxy persistenceApiProxy;

  @InjectMocks
  private OrderController orderController;

  @Test
  void retrieveOrders_ShouldCallProxyRetrieveOrders() {
    orderController.retrieveOrders();

    verify(persistenceApiProxy).retrieveOrders();
  }

  @Test
  void retrieveOrder_ShouldCallProxyRetrieveOrder() {
    orderController.retrieveOrder(ORDER_ID);

    verify(persistenceApiProxy).retrieveOrder(ORDER_ID);
  }

  @Test
  void createOrder_ShouldCallProxyCreateOrder() {
    orderController.createOrder(orderRequestDTO);

    verify(persistenceApiProxy).createOrder(orderRequestDTO);
  }

  @Test
  void deleteOrder_ShouldCallProxyDeleteOrder() {
    orderController.deleteOrder(ORDER_ID);

    verify(persistenceApiProxy).deleteOrder(ORDER_ID);
  }

  private OrderRequestDTO generateOrderRequestDTO() {
    return new OrderRequestDTO(ORDER_OWNER);
  }

}
