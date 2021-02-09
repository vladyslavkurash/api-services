package com.vladyslavkurash.orders.persistenceapiservice.exceptions;

public class OrderNotFoundException extends RuntimeException {

  public OrderNotFoundException(String message) {
    super(message);
  }

}
