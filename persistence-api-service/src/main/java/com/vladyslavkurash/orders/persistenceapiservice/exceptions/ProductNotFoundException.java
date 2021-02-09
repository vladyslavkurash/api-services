package com.vladyslavkurash.orders.persistenceapiservice.exceptions;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(String message) {
    super(message);
  }

}
