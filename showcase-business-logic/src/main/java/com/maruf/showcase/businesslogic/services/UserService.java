package com.maruf.showcase.businesslogic.services;

import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.User;
import com.maruf.showcase.dto.UserDto;
import com.maruf.showcase.persistence.repositories.CityRepository;
import com.maruf.showcase.persistence.repositories.CountryRepository;
import com.maruf.showcase.persistence.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final CountryRepository countryRepository;
  private final CityRepository cityRepository;

  @Autowired
  public UserService(UserRepository userRepository, CountryRepository countryRepository,
      CityRepository cityRepository) {
    this.userRepository = userRepository;
    this.countryRepository = countryRepository;
    this.cityRepository = cityRepository;
  }

  public List<Country> getOriginCountries() {
    return countryRepository.findDistinctUserbaseCountries();
  }

  public UserDto createUser(UserDto userDto) {
    User newUser = userDtoToUser(userDto);
    userRepository.save(newUser);
    return userDto;
  }

  private User userDtoToUser(UserDto userDto) {
    return User.builder()
        .name(userDto.getName())
        .surname(userDto.getSurname())
        .email(userDto.getEmail())
        .username(userDto.getUsername())
        .dateOfBirth(userDto.getDateOfBirth())
        .city(cityRepository.getReferenceById(userDto.getCityId()))
        .build();
  }
}
