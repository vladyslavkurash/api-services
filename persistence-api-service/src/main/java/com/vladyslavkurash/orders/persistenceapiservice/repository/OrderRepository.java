package com.vladyslavkurash.orders.persistenceapiservice.repository;

import com.vladyslavkurash.orders.persistenceapiservice.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
