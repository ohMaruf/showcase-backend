package com.maruf.showcase.businesslogic;

import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.persistence.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final CountryRepository countryRepository;

  @Autowired
  public UserService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public List<Country> getAllCountries() {
    return countryRepository.findAll();
  }
}
