package com.maruf.showcase.boot.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maruf.showcase.boot.builders.ServiceTestDataBuilder;
import com.maruf.showcase.businesslogic.services.UserService;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.dto.UserDto;
import com.maruf.showcase.web.controllers.UserController;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class)
@Import(UserController.class)
@TestInstance(Lifecycle.PER_METHOD)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private UserService userService;
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void whenGetOriginCountries_thenReturnOk() throws Exception {
    List<Country> expectedCountries = List.of(ServiceTestDataBuilder.randomCountry());
    doReturn(expectedCountries).when(userService).getOriginCountries();
    String expected = objectMapper.writeValueAsString(expectedCountries);

    String actual = mockMvc.perform(
            get("/v1/api/user/origin-countries").accept(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  void givenNewUser_whenCreateNewUser_thenReturnUserDtoAndOk() throws Exception {
    UserDto expectedDto = ServiceTestDataBuilder.randomUserDto();
    String expected = objectMapper.writeValueAsString(expectedDto);
    doReturn(expectedDto).when(userService).createUser(expectedDto);

    String actual = mockMvc.perform(
            post("/v1/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expected))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  void givenNewUserWithInvalidBirthday_whenCreateNewUser_thenReturnBadRequest() throws Exception {
    UserDto expectedDto = ServiceTestDataBuilder.randomUserDto();
    expectedDto.setDateOfBirth(LocalDate.now().plusDays(1));
    String expected = objectMapper.writeValueAsString(expectedDto);
    doReturn(expectedDto).when(userService).createUser(expectedDto);

    mockMvc.perform(
        post("/v1/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expected))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andReturn().getResponse().getContentAsString();
  }
}
