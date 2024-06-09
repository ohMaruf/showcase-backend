package com.maruf.showcase.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSummaryDto {

  private String name;
  private String description;
  private Float averageRating;
  private List<ReviewDto> reviews;
  private SellerDto seller;
  private Integer sales;

}
