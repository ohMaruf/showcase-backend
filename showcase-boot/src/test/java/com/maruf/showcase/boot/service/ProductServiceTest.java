package com.maruf.showcase.boot.service;

import static org.mockito.Mockito.doReturn;

import com.maruf.showcase.boot.builders.ServiceTestDataBuilder;
import com.maruf.showcase.businesslogic.services.ProductService;
import com.maruf.showcase.domain.model.Product;
import com.maruf.showcase.domain.model.Review;
import com.maruf.showcase.domain.model.User;
import com.maruf.showcase.dto.ProductSummaryDto;
import com.maruf.showcase.dto.ReviewDto;
import com.maruf.showcase.dto.SellerDto;
import com.maruf.showcase.persistence.repositories.OrderRepository;
import com.maruf.showcase.persistence.repositories.ProductRepository;
import com.maruf.showcase.persistence.repositories.ReviewRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;
  @Mock
  private ReviewRepository reviewRepository;
  @Mock
  private OrderRepository orderRepository;
  @InjectMocks
  private ProductService productService;

  @Test
  void givenExistingProductId_whenGetProductSummary_thenReturnProductSummary() {
    Product expectedProduct = ServiceTestDataBuilder.randomProduct();
    List<Review> expectedReviews = List.of(
        ServiceTestDataBuilder.randomReview(expectedProduct),
        ServiceTestDataBuilder.randomReview(expectedProduct)
    );
    Integer expectedSales = 2;
    Float expectedAverageRating = expectedReviews.stream()
        .map(Review::getRating)
        .collect(Collectors.averagingInt(Short::toUnsignedInt))
        .floatValue();
    ProductSummaryDto expectedDto = ProductSummaryDto.builder()
        .name(expectedProduct.getName())
        .description(expectedProduct.getDescription())
        .averageRating(expectedAverageRating)
        .sales(expectedSales)
        .reviews(toReviewDtoList(expectedReviews))
        .seller(toSellerDto(expectedProduct.getSeller()))
        .build();

    doReturn(Optional.of(expectedProduct)).when(productRepository).findById(expectedProduct.getId());
    doReturn(expectedReviews).when(reviewRepository).findByProduct(expectedProduct.getId());
    doReturn(expectedAverageRating).when(reviewRepository).averageRatingByProduct(expectedProduct.getId());
    doReturn(expectedSales).when(orderRepository).countByProduct(expectedProduct.getId());

    ProductSummaryDto actualDto = productService.getProductById(expectedProduct.getId());

    Assertions.assertThat(actualDto).isNotNull();
    Assertions.assertThat(actualDto).isEqualTo(expectedDto);
  }

  private SellerDto toSellerDto(User seller) {
    return SellerDto.builder()
        .id(seller.getId())
        .username(seller.getUsername())
        .build();
  }

  private List<ReviewDto> toReviewDtoList(List<Review> reviews) {
    return reviews.stream().map(this::toReviewDto).toList();
  }

  private ReviewDto toReviewDto(Review review) {
    return ReviewDto.builder()
        .authorName(review.getAuthor().getFullName())
        .textContent(review.getDescription())
        .rating(review.getRating())
        .build();
  }
}
