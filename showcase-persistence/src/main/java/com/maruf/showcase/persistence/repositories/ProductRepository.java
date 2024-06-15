package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
