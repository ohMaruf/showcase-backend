package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

}
