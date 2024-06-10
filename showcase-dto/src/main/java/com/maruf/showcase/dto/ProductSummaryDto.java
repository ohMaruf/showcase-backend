package com.maruf.showcase.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "Name field cannot be blank")
  @NotNull(message = "Name field is mandatory")
  private String name;
  @NotBlank(message = "Description field cannot be blank")
  @NotNull(message = "Description field is mandatory")
  private String description;
  @NotNull(message = "Average rating field is mandatory")
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
