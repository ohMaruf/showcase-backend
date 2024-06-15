package com.maruf.showcase.boot.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.maruf.showcase.boot.builders.ServiceTestDataBuilder;
import com.maruf.showcase.businesslogic.services.UserService;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.User;
import com.maruf.showcase.dto.CountryDto;
import com.maruf.showcase.dto.UserDto;
import com.maruf.showcase.persistence.repositories.CityRepository;
import com.maruf.showcase.persistence.repositories.CountryRepository;
import com.maruf.showcase.persistence.repositories.UserRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;
  @Mock
  private CountryRepository countryRepository;
  @Mock
  private CityRepository cityRepository;
  @InjectMocks
  private UserService userService;

  @Test
  void whenGetAllOriginCountries_thenReturnCountries() {
    List<Country> expectedCountries = List.of(ServiceTestDataBuilder.randomCountry());
    doReturn(expectedCountries).when(countryRepository).findDistinctUserbaseCountries();

    List<CountryDto> actual = userService.getOriginCountries();

    Assertions.assertThat(actual).hasSameSizeAs(expectedCountries);
  }

  @Test
  void whenCreateUser_thenReturnUserDto() {
    UserDto expected = ServiceTestDataBuilder.randomUserDto();
    User expectedUser = User.builder()
        .name(expected.getName())
        .surname(expected.getSurname())
        .email(expected.getEmail())
        .username(expected.getUsername())
        .dateOfBirth(expected.getDateOfBirth())
        .city(cityRepository.getReferenceById(expected.getCityId()))
        .build();
    doReturn(expectedUser).when(userRepository).save(any());

    UserDto actual = userService.createUser(expected);

    Assertions.assertThat(actual).isEqualTo(expected);
  }

  private List<CountryDto> countryListToCountryDtoList(List<Country> countryList) {
    return countryList.stream().map(this::contryToCountryDto).toList();
  }

  private CountryDto contryToCountryDto(Country country) {
    return CountryDto.builder()
        .code(country.getCode())
        .name(country.getName())
        .build();
  }
}
