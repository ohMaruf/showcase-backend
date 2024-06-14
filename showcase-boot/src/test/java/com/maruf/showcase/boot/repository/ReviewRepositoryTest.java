package com.maruf.showcase.boot.repository;

import com.maruf.showcase.boot.builders.PersistenceTestDataBuilder;
import com.maruf.showcase.domain.model.Product;
import com.maruf.showcase.domain.model.Review;
import com.maruf.showcase.domain.model.User;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReviewRepositoryTest extends AbstractRepositoryTest {

  @BeforeEach
  void setUpBeforeEach() {
    cleanEnvironment();
  }

  @AfterEach
  void tearDownAfterEach() {
    cleanEnvironment();
  }

  @Test
  void givenNewReview_whenSaveReview_thenSuccess() {
    Review expected = PersistenceTestDataBuilder.randomReview(getPersistedTestUser(), getPersistedTestProduct());

    Review actual = reviewRepository.save(expected);

    Assertions.assertThat(actual.getId()).isNotNull().isPositive();
  }

  @Test
  void givenProductWithNoReviews_whenGetAverageRating_thenReturnNull() {
    Float actual = reviewRepository.averageRatingByProduct(getPersistedTestProduct().getId());

    System.out.println(actual);

    Assertions.assertThat(actual).isNull();
  }

  @Test
  void givenProductWithAFiveStarReview_whenGetAverageRating_thenReturnFive() {
    Product testProduct = getPersistedTestProduct();
    Review fiveStarReview = PersistenceTestDataBuilder.randomReview(
        getPersistedTestUser(),
        testProduct,
        (short) 5);
    reviewRepository.save(fiveStarReview);

    Float actual = reviewRepository.averageRatingByProduct(testProduct.getId());

    Assertions.assertThat(actual).isNotNull().isEqualTo(5.0f);
  }

  @Test
  void givenProductWithMultipleReviews_whenAverageRatingByProduct_thenReturnMeanOfReviewsStars() {
    Product testProduct = getPersistedTestProduct();
    List<Review> reviews = List.of(
        PersistenceTestDataBuilder.randomReview(getPersistedTestUser(), testProduct),
        PersistenceTestDataBuilder.randomReview(getPersistedTestUser(), testProduct)
    );
    reviewRepository.saveAll(reviews);

    Float expected = reviews.stream()
        .map(Review::getRating)
        .collect(Collectors.averagingInt(Short::toUnsignedInt))
        .floatValue();

    Float actual = reviewRepository.averageRatingByProduct(testProduct.getId());

    Assertions.assertThat(actual).isNotNull().isEqualTo(expected);
  }

  @Test
  void givenProductWithNoReviews_whenFindByProduct_thenReturnEmptyList() {
    Product testProduct = getPersistedTestProduct();
    List<Review> reviews = reviewRepository.findByProduct(testProduct.getId());

    Assertions.assertThat(reviews).isEmpty();
  }

  @Test
  void givenProductWithMultipleReviews_whenFindByProduct_thenReturnReviews() {
    Product testProduct = getPersistedTestProduct();
    List<Review> expected = List.of(
        PersistenceTestDataBuilder.randomReview(getPersistedTestUser(), testProduct),
        PersistenceTestDataBuilder.randomReview(getPersistedTestUser(), testProduct),
        PersistenceTestDataBuilder.randomReview(getPersistedTestUser(), testProduct),
        PersistenceTestDataBuilder.randomReview(getPersistedTestUser(), testProduct)
    );
    reviewRepository.saveAll(expected);

    List<Review> actual = reviewRepository.findByProduct(testProduct.getId());

    Assertions.assertThat(actual).hasSameSizeAs(expected);
  }
}
