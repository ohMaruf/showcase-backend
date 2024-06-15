package com.maruf.showcase.boot.builders;

import com.maruf.showcase.domain.model.City;
import com.maruf.showcase.domain.model.Country;
import com.maruf.showcase.domain.model.Product;
import com.maruf.showcase.domain.model.Review;
import com.maruf.showcase.domain.model.State;
import com.maruf.showcase.domain.model.User;
import com.maruf.showcase.dto.ProductSummaryDto;
import com.maruf.showcase.dto.ReviewDto;
import com.maruf.showcase.dto.SellerDto;
import com.maruf.showcase.dto.UserDto;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceTestDataBuilder {

  private static int counter = 0;

  public static ProductSummaryDto randomProductSummaryDto() {
    counter++;
    Product randomProduct = randomProduct();
    List<ReviewDto> randomReviews = List.of(randomReviewDto(randomProduct), randomReviewDto(randomProduct));
    Float averageRating = randomReviews.stream()
        .map(ReviewDto::getRating)
        .collect(Collectors.averagingInt(Short::toUnsignedInt))
        .floatValue();
    return ProductSummaryDto.builder()
        .name(randomProduct.getName())
        .description(randomProduct.getDescription())
        .reviews(randomReviews)
        .seller(randomSellerDto())
        .sales(3)
        .averageRating(averageRating)
        .build();
  }

  public static SellerDto randomSellerDto() {
    counter++;
    User randomUser = randomUser();
    return SellerDto.builder()
        .id(randomUser.getId())
        .username(randomUser.getUsername())
        .build();
  }

  public static UserDto randomUserDto() {
    counter++;
    return UserDto.builder()
        .name("TestUser" + counter)
        .surname("TestSurname" + counter)
        .dateOfBirth(LocalDate.now().minusDays(1))
        .email("test" + counter + "@email.com")
        .username("testUsername" + counter)
        .cityId(1)
        .build();
  }

  public static ReviewDto randomReviewDto(Product product) {
    counter++;
    Review randomReview = randomReview(product);
    return ReviewDto.builder()
        .authorName(randomUser().getFullName())
        .rating(randomReview.getRating())
        .textContent(randomReview.getDescription())
        .build();
  }

  public static Product randomProduct() {
    return PersistenceTestDataBuilder.randomProduct(randomUser());
  }

  public static Review randomReview(Product product) {
    return PersistenceTestDataBuilder.randomReview(randomUser(), product);
  }

  public static User randomUser() {
    return PersistenceTestDataBuilder.randomUser(randomCity());
  }

  public static City randomCity() {
    return PersistenceTestDataBuilder.randomCity(randomState());
  }

  public static State randomState() {
    return PersistenceTestDataBuilder.randomState(randomCountry());
  }

  public static Country randomCountry() {
    return PersistenceTestDataBuilder.randomCountry();
  }
}
