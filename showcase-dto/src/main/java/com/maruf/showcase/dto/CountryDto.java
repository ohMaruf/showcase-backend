package com.maruf.showcase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDto {

  @NotNull(message = "country code is mandatory")
  @Size(min = 2, max = 2, message = "country code must be exactly 2 characters long")
  private String code;
  @NotBlank(message = "country name cannot be blank")
  @NotNull(message = "country name is mandatory")
  @Size(min = 2, max = 50)
  private String name;
}
