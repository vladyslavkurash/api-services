package com.vladyslavkurash.orders.persistenceapiservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.vladyslavkurash.orders.persistenceapiservice.service.OrderService;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderRequestDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.OrderResponseDTO;
import com.vladyslavkurash.orders.persistenceapiservice.transfer.dto.ProductResponseDTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

  private final Long ORDER_ID = 1L;
  private final String ORDER_OWNER = "Some owner";
  private final Long PRODUCT_ID = 5L;
  private final String PRODUCT_NAME = "Some name";
  private final Double PRODUCT_PRICE = 10.5;
  private final OrderRequestDTO orderRequestDTO = generateOrderRequestDTO();
  private final OrderResponseDTO orderResponseDTO = generateOrderResponseDTO();
  private final List<OrderResponseDTO> orders = List.of(orderResponseDTO);

  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  @Test
  void retrieveOrders_ShouldCallServiceAndReturnOrders() {
    when(orderService.retrieveOrders()).thenReturn(orders);

    List<OrderResponseDTO> expected = orders;
    List<OrderResponseDTO> actual = orderController.retrieveOrders().getBody();

    assertEquals(expected, actual);
    verify(orderService).retrieveOrders();
  }

  @Test
  void retrieveOrder_ShouldCallServiceAndReturnOrder() {
    when(orderService.retrieveOrder(ORDER_ID)).thenReturn(orderResponseDTO);

    OrderResponseDTO expected = orderResponseDTO;
    OrderResponseDTO actual = orderController.retrieveOrder(ORDER_ID).getBody();

    assertEquals(expected, actual);
    verify(orderService).retrieveOrder(ORDER_ID);
  }

  @Test
  void createOrder_ShouldCallServiceAndReturnOrder() {
    when(orderService.createOrder(orderRequestDTO)).thenReturn(orderResponseDTO);

    OrderResponseDTO expected = orderResponseDTO;
    OrderResponseDTO actual = orderController.createOrder(orderRequestDTO).getBody();

    assertEquals(expected, actual);
    verify(orderService).createOrder(orderRequestDTO);
  }

  @Test
  void deleteOrder_ShouldCallServiceAndReturnNothing() {
    orderController.deleteOrder(ORDER_ID);

    verify(orderService).deleteOrder(ORDER_ID);
  }

  private OrderRequestDTO generateOrderRequestDTO() {
    return new OrderRequestDTO(ORDER_OWNER);
  }

  private OrderResponseDTO generateOrderResponseDTO() {
    return new OrderResponseDTO(ORDER_ID, ORDER_OWNER, generateOrderResponseProductList());
  }

  private List<ProductResponseDTO> generateOrderResponseProductList() {
    List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
    productResponseDTOList.add(generateProductResponseDTO());
    return productResponseDTOList;
  }

  private ProductResponseDTO generateProductResponseDTO() {
    return new ProductResponseDTO(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE);
  }

}
