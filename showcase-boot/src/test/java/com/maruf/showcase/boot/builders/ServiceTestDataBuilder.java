package com.maruf.showcase.boot.builders;

import com.maruf.showcase.domain.model.City;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.Product;
import com.maruf.showcase.domain.model.Review;
import com.maruf.showcase.domain.model.State;
import com.maruf.showcase.domain.model.User;
import com.maruf.showcase.dto.UserDto;
import java.time.LocalDate;

public class ServiceTestDataBuilder {

  private static int counter = 0;

  public static UserDto randomUserDto() {
    counter++;
    return UserDto.builder()
        .name("TestUser" + counter)
        .surname("TestSurname" + counter)
        .dateOfBirth(LocalDate.now().minusDays(1))
        .email("test" + counter + "@email.com")
        .username("testUsername" + counter)
        .cityId(1)
        .build();
  }

  public static Product randomProduct() {
    return PersistenceTestDataBuilder.randomProduct(randomUser());
  }

  public static Review randomReview(Product product) {
    return PersistenceTestDataBuilder.randomReview(randomUser(), product);
  }

  public static User randomUser() {
    return PersistenceTestDataBuilder.randomUser(randomCity());
  }

  public static City randomCity() {
    return PersistenceTestDataBuilder.randomCity(randomState());
  }

  public static State randomState() {
    return PersistenceTestDataBuilder.randomState(randomCountry());
  }

  public static Country randomCountry() {
    return PersistenceTestDataBuilder.randomCountry();
  }
}
