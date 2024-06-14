package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
