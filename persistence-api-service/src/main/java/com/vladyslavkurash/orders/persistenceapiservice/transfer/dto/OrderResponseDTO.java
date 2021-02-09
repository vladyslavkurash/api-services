package com.vladyslavkurash.orders.persistenceapiservice.transfer.dto;

import java.util.List;

public class OrderResponseDTO {

  private Long id;
  private String owner;
  private List<ProductResponseDTO> products;

  public OrderResponseDTO() {
  }

  public OrderResponseDTO(Long id, String owner,
      List<ProductResponseDTO> products) {
    this.id = id;
    this.owner = owner;
    this.products = products;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public List<ProductResponseDTO> getProducts() {
    return products;
  }

  public void setProducts(
      List<ProductResponseDTO> products) {
    this.products = products;
  }
}
