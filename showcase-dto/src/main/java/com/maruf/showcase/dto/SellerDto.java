package com.maruf.showcase.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SellerDto {

  @NotNull(message = "Id field is mandatory")
  @Min(0)
  private Integer id;

  @NotBlank(message = "Username field cannot be blank")
  @NotNull(message = "Username field is mandatory")
  private String username;
}
