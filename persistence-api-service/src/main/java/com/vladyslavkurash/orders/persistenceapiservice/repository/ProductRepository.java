package com.vladyslavkurash.orders.persistenceapiservice.repository;

import com.vladyslavkurash.orders.persistenceapiservice.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByOrder_Id(Long orderId);

  Optional<Product> findByOrder_IdAndId(Long orderId, Long id);

  void deleteByOrder_IdAndId(Long orderId, Long id);
}
