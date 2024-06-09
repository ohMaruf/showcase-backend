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
    ProductSummaryDto productSummaryDto = new ProductSummaryDto();
    productSummaryDto.setName(product.getName());
    productSummaryDto.setDescription(product.getDescription());
    productSummaryDto.setAverageRating(averageRating);
    productSummaryDto.setSales(sales);
    productSummaryDto.setReviews(toReviewDtoList(reviews));
    productSummaryDto.setSeller(toSellerDto(product.getSeller()));
    return productSummaryDto;
  }

  private SellerDto toSellerDto(User seller) {
    SellerDto sellerDto = new SellerDto();
    sellerDto.setId(seller.getId());
    sellerDto.setUsername(seller.getUsername());
    return sellerDto;
  }

  private List<ReviewDto> toReviewDtoList(List<Review> reviews) {
    return reviews.stream().map(this::toReviewDto).toList();
  }

  private ReviewDto toReviewDto(Review review) {
    ReviewDto reviewDto = new ReviewDto();
    reviewDto.setAuthorName(review.getAuthor().getFullName());
    reviewDto.setTextContent(review.getDescription());
    reviewDto.setRating(review.getRating());
    return reviewDto;
  }
}
