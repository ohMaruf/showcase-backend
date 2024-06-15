package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {

}
