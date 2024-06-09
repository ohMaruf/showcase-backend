package com.maruf.showcase.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SellerDto {

  @NotNull(message = "seller id field is mandatory")
  private Integer id;
  @NotNull(message = "seller username field is mandatory")
  private String username;
}
