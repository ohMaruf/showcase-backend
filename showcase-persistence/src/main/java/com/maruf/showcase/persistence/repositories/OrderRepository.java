package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Integer> {

  @Query("SELECT COUNT(o) FROM Order o WHERE o.product.id = :productId")
  Integer countByProduct(@Param("productId") Integer productId);
}
