package com.maruf.showcase.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class ProductSummaryDto {

  @NotBlank(message = "Name field cannot be blank")
  @NotNull(message = "Name field is mandatory")
  private String name;
  @NotNull(message = "Description field is mandatory")
  private String description;
  @Range(min = 1, max = 5)
  private Float averageRating;
  @Valid
  private List<ReviewDto> reviews;
  @Valid
  private SellerDto seller;
  @NotNull(message = "Sales field is mandatory")
  @Min(0)
  private Integer sales;

}
