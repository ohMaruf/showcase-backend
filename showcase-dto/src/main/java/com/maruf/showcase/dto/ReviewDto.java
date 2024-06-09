package com.maruf.showcase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ReviewDto {

  @NotBlank(message = "Author name field cannot be blank")
  @NotNull(message = "Author name field is mandatory")
  private String authorName;
  @NotNull(message = "Text content field is mandatory")
  private String textContent;
  @NotNull(message = "Rating field is mandatory")
  @Range(min = 1, max = 5)
  private Short rating;
}
