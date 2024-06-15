package com.maruf.showcase.boot.repository;

import com.maruf.showcase.boot.builders.PersistenceTestDataBuilder;
import com.maruf.showcase.domain.model.User;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

class UserRepositoryTest extends AbstractRepositoryTest {

  @BeforeAll
  static void setUpBeforeClass() {
  }

  @BeforeEach
  void setUpBeforeEach() {
    cleanEnvironment();
  }

  @AfterEach
  void tearDownAfterEach() {
    cleanEnvironment();
  }

  @Test
  void givenNewUser_whenSave_thenSuccess() {
    User expected = getTransientTestUser();
    User actual = userRepository.save(expected);

    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getId()).isPositive();
  }

  @Test
  void givenTwoNewUsers_whenSave_thenSuccess() {
    List<User> expected = List.of(
        getTransientTestUser(),
        getTransientTestUser()
    );

    List<User> actual = userRepository.saveAll(expected);

    Assertions.assertThat(actual).isNotNull().hasSameSizeAs(expected);
  }

  @Test
  void givenUser_whenDelete_thenSuccess() {
    User testUser = getTransientTestUser();
    userRepository.save(testUser);

    userRepository.delete(testUser);
    List<User> users = userRepository.findAll();

    Assertions.assertThat(users).isEmpty();
  }

  @Test
  void givenUserWithEmailAssociatedToAnotherUser_whenSave_thenThrowException() {
    User testUser = getTransientTestUser();
    userRepository.save(testUser);

    User duplicateUser = PersistenceTestDataBuilder.randomUser(testUser.getCity());
    duplicateUser.setEmail(testUser.getEmail());

    Assertions.assertThatThrownBy(() -> userRepository.save(duplicateUser))
        .isInstanceOf(DataIntegrityViolationException.class);
  }

  @Test
  void givenUserWithUsernameAssociatedToAnotherUser_whenSave_thenThrowException() {
    User testUser = getTransientTestUser();
    userRepository.save(testUser);

    User duplicateUser = PersistenceTestDataBuilder.randomUser(testUser.getCity());
    duplicateUser.setUsername(testUser.getUsername());

    Assertions.assertThatThrownBy(() -> userRepository.save(duplicateUser))
        .isInstanceOf(DataIntegrityViolationException.class);
  }
}
