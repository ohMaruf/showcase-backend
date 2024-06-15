package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountryRepository extends JpaRepository<Country, Long> {

  @Query("SELECT DISTINCT(c) FROM Country c "
      + "INNER JOIN State s ON s.country = c "
      + "INNER JOIN City ci ON ci.state = s "
      + "INNER JOIN User u ON u.city = ci")
  List<Country> findDistinctUserbaseCountries();
}
