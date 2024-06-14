package com.maruf.showcase.boot.repository;

import com.maruf.showcase.boot.builders.PersistenceTestDataBuilder;
import com.maruf.showcase.domain.model.City;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.User;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CountryRepositoryTest extends AbstractRepositoryTest {

  @BeforeEach
  void setUpBeforeEach() {
    cleanEnvironment();
  }

  @AfterEach
  void tearDownAfterEach() {
    cleanEnvironment();
  }

  @Test
  void givenNoUsers_whenFindDistinctUserbaseCountries_thenReturnEmptyList() {
    List<Country> countries = countryRepository.findDistinctUserbaseCountries();

    Assertions.assertThat(countries).isEmpty();
  }

  @Test
  void givenMultipleUsersFromSameCountry_whenFindDistinctUserbaseCountries_thenReturnCountry() {
    City testCity = getPersistedTestCity();
    List<User> users = List.of(
        PersistenceTestDataBuilder.randomUser(testCity),
        PersistenceTestDataBuilder.randomUser(testCity)
    );
    userRepository.saveAll(users);

    List<Country> countries = countryRepository.findDistinctUserbaseCountries();

    Assertions.assertThat(countries).hasSize(1);
    Assertions.assertThat(countries.getFirst().getName()).isEqualTo(testCity.getState().getCountry().getName());
  }

  @Test
  void givenMultipleUsersFromDifferentCountry_whenFindDistinctUserbaseCountries_thenReturnDistinctCountries() {
    City testCity = getPersistedTestCity();
    List<User> users = List.of(
        PersistenceTestDataBuilder.randomUser(testCity),
        PersistenceTestDataBuilder.randomUser(getPersistedTestCity())
    );
    userRepository.saveAll(users);

    List<Country> countries = countryRepository.findDistinctUserbaseCountries();

    Assertions.assertThat(countries).hasSize(2);
  }
}
