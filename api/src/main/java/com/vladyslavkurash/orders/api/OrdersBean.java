package com.vladyslavkurash.orders.api;

public class OrdersBean {

  private Long id;
  private String owner;

  public OrdersBean() {
  }

  public OrdersBean(Long id, String owner) {
    this.id = id;
    this.owner = owner;
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
}
