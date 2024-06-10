package com.maruf.showcase.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@TestInstance(Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
class UserTest {

  private static final String NAME = "name";
  private static final String SURNAME = "surname";

  private User user;

  @BeforeAll
  void setUp() {
    user = new User();
    user.setName(NAME);
    user.setSurname(SURNAME);
  }

  @Test
  void whenGetFullName_thenReturnSpaceSeparatedNameAndSurname() {
    String expected = NAME + " " + SURNAME;
    String actual = user.getFullName();
    Assertions.assertEquals(expected, actual);
  }
}
