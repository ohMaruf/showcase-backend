package com.maruf.showcase.persistence.repositories;

import com.maruf.showcase.domain.model.Product;
import com.maruf.showcase.domain.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends CrudRepository<Review, Long> {

  @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
  List<Review> findByProduct(@Param("productId") Integer productId);

  @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId")
  Float averageRatingByProduct(@Param("productId") Integer productId);

}
