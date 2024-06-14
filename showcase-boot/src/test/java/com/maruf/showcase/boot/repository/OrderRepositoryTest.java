package com.maruf.showcase.boot.repository;

import com.maruf.showcase.boot.builders.PersistenceTestDataBuilder;
import com.maruf.showcase.domain.model.Order;
import com.maruf.showcase.domain.model.Product;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderRepositoryTest extends AbstractRepositoryTest {

  @BeforeEach
  void setUpBeforeEach() {
    cleanEnvironment();
  }

  @AfterEach
  void tearDownAfterEach() {
    cleanEnvironment();
  }

  @Test
  void givenNoOrders_whenCountByProductId_thenReturnZero() {
    Integer nOrders = orderRepository.countByProduct(getTransientTestProduct().getId());

    Assertions.assertThat(nOrders).isZero();
  }

  @Test
  void givenMultipleOrders_whenCountByProductId_thenReturnNumberOfOrders() {
    Product testProduct = getPersistedTestProduct();
    List<Order> orders = List.of(
        PersistenceTestDataBuilder.randomOrder(getPersistedTestUser(), testProduct),
        PersistenceTestDataBuilder.randomOrder(getPersistedTestUser(), testProduct),
        PersistenceTestDataBuilder.randomOrder(getPersistedTestUser(), testProduct)
    );
    orderRepository.saveAll(orders);

    Integer nOrders = orderRepository.countByProduct(testProduct.getId());

    Assertions.assertThat(nOrders).isEqualTo(orders.size());
  }
}
