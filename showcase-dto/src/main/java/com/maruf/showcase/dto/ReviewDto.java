package com.maruf.showcase.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ReviewDto {

  @NotNull(message = "review id field is mandatory")
  private String authorName;
  @NotNull(message = "review textContent field is mandatory")
  private String textContent;
  @NotNull(message = "review start field is mandatory")
  @Range(min = 1, max = 5)
  private Short rating;
}
