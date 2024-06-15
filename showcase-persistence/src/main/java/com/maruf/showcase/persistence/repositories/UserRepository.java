package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
