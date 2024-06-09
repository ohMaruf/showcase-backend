package com.maruf.showcase.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Data
public class ProductSummaryDto {

  @NotNull(message = "product name field is mandatory")
  private String name;
  @NotNull(message = "product description field is mandatory")
  private String description;
  @NotNull(message = "product averageRating field is mandatory")
  @Range(min = 1, max = 5)
  private Float averageRating;
  @Valid
  private List<ReviewDto> reviews;
  @Valid
  private SellerDto seller;
  @NotNull(message = "product name field is mandatory")
  @Min(0)
  private Integer sales;

}
