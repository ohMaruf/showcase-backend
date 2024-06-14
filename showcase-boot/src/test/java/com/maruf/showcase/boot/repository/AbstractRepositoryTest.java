package com.maruf.showcase.boot.repository;

import com.maruf.showcase.boot.builders.PersistenceTestDataBuilder;
import com.maruf.showcase.domain.model.City;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.Product;
import com.maruf.showcase.domain.model.State;
import com.maruf.showcase.domain.model.User;
import com.maruf.showcase.persistence.repositories.CityRepository;
import com.maruf.showcase.persistence.repositories.CountryRepository;
import com.maruf.showcase.persistence.repositories.OrderRepository;
import com.maruf.showcase.persistence.repositories.ProductRepository;
import com.maruf.showcase.persistence.repositories.ReviewRepository;
import com.maruf.showcase.persistence.repositories.StateRepository;
import com.maruf.showcase.persistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(Lifecycle.PER_METHOD)
@Execution(ExecutionMode.CONCURRENT)
public abstract class AbstractRepositoryTest {

  @PersistenceContext
  protected EntityManager entityManager;
  @Autowired
  protected OrderRepository orderRepository;
  @Autowired
  protected ReviewRepository reviewRepository;
  @Autowired
  protected ProductRepository productRepository;
  @Autowired
  protected UserRepository userRepository;
  @Autowired
  protected CityRepository cityRepository;
  @Autowired
  protected StateRepository stateRepository;
  @Autowired
  protected CountryRepository countryRepository;

  protected void cleanEnvironment() {
    entityManager.clear();
    userRepository.deleteAll();
    cityRepository.deleteAll();
    stateRepository.deleteAll();
    countryRepository.deleteAll();
  }

  protected Product getTransientTestProduct() {
    User seller = getPersistedTestUser();
    return PersistenceTestDataBuilder.randomProduct(seller);
  }

  protected User getTransientTestUser() {
    City randomCity = getPersistedTestCity();
    return PersistenceTestDataBuilder.randomUser(randomCity);
  }

  protected Product getPersistedTestProduct() {
    return productRepository.save(getTransientTestProduct());
  }

  protected User getPersistedTestUser() {
    return userRepository.save(getTransientTestUser());
  }

  protected City getPersistedTestCity() {
    Country randomCountry = countryRepository.save(PersistenceTestDataBuilder.randomCountry());
    State randomState = stateRepository.save(PersistenceTestDataBuilder.randomState(randomCountry));
    return cityRepository.save(PersistenceTestDataBuilder.randomCity(randomState));
  }
}
