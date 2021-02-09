package com.vladyslavkurash.orders.persistenceapiservice.transfer.dto;

public class ProductRequestDTO {

  private String name;
  private Double price;

  public ProductRequestDTO() {
  }

  public ProductRequestDTO(String name, Double price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }
}
