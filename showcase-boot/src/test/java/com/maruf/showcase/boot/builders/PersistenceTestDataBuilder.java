package com.maruf.showcase.boot.builders;

import com.maruf.showcase.domain.enumerations.OrderStatus;
import com.maruf.showcase.domain.model.City;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.Order;
import com.maruf.showcase.domain.model.Product;
import com.maruf.showcase.domain.model.Review;
import com.maruf.showcase.domain.model.State;
import com.maruf.showcase.domain.model.User;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDate;

public class PersistenceTestDataBuilder {

  private static int counter = 0;
  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  public static Product randomProduct(User seller) {
    counter++;
    return Product.builder()
        .name("test name " + counter)
        .description("test description " + counter)
        .price(BigDecimal.valueOf(RANDOM.nextDouble()))
        .seller(seller)
        .build();
  }

  public static Country randomCountry() {
    counter++;
    return Country.builder()
        .name("TestCountry" + counter)
        .code(randomCode())
        .build();
  }

  public static State randomState(Country country) {
    counter++;
    return State.builder()
        .name("TestState" + counter)
        .country(country)
        .build();
  }

  public static City randomCity(State state) {
    counter++;
    return City.builder()
        .name("TestCity" + counter)
        .code(randomCode())
        .state(state)
        .build();
  }

  public static Order randomOrder(User customer, Product product) {
    counter++;
    return Order.builder()
        .status(OrderStatus.DELIVERED)
        .quantity((short) counter)
        .customer(customer)
        .product(product)
        .build();
  }

  public static Review randomReview(User author, Product product) {
    return randomReview(author, product, (short) RANDOM.nextInt(0, 5));
  }

  public static Review randomReview(User author, Product product, Short rating) {
    counter++;
    return Review.builder()
        .description("test description " + counter)
        .author(author)
        .product(product)
        .rating(rating)
        .build();
  }

  public static User randomUser(City city) {
    counter++;
    return User.builder()
        .name("TestUser" + counter)
        .surname("TestSurname" + counter)
        .dateOfBirth(LocalDate.now().minusDays(1))
        .email("test" + counter + "@email.com")
        .username("testUsername" + counter)
        .city(city)
        .build();
  }

  private static String randomCode() {
    return String.valueOf(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())))
        + ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()));
  }
}
