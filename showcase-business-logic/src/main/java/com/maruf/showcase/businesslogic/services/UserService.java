package com.maruf.showcase.businesslogic.services;

import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.persistence.repositories.CountryRepository;
import com.maruf.showcase.persistence.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final CountryRepository countryRepository;

  @Autowired
  public UserService(UserRepository userRepository, CountryRepository countryRepository) {
    this.userRepository = userRepository;
    this.countryRepository = countryRepository;
  }

  public List<Country> getOriginCountries() {
    return countryRepository.findDistinctUserbaseCountries();
  }
}
