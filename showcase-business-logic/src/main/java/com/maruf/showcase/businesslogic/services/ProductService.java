package com.maruf.showcase.businesslogic.services;

import com.maruf.showcase.businesslogic.exceptions.ResourceNotFoundException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final ReviewRepository reviewRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository1,
      OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.reviewRepository = reviewRepository1;
    this.orderRepository = orderRepository;
  }

  public ProductSummaryDto getProductById(Integer id) throws ResourceNotFoundException {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product does not exist"));

    List<Review> reviews = reviewRepository.findByProduct(product.getId());
    Integer sales = orderRepository.countByProduct(product.getId());
    Float averageRating = reviewRepository.averageRatingByProduct(product.getId());

    return toProductSummaryDto(product, reviews, sales, averageRating);
  }

  private ProductSummaryDto toProductSummaryDto(Product product, List<Review> reviews,
      Integer sales, Float averageRating) {
    return ProductSummaryDto.builder()
        .name(product.getName())
        .description(product.getDescription())
        .averageRating(averageRating)
        .sales(sales)
        .reviews(toReviewDtoList(reviews))
        .seller(toSellerDto(product.getSeller()))
        .build();
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
