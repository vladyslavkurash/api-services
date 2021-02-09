package com.vladyslavkurash.orders.persistenceapiservice.transfer.dto;

public class OrderRequestDTO {

  private String owner;

  public OrderRequestDTO() {
  }

  public OrderRequestDTO(String owner) {
    this.owner = owner;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }
}
