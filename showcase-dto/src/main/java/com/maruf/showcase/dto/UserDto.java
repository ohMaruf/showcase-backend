package com.maruf.showcase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserDto {

  @NotBlank(message = "Name field cannot be blank")
  @NotNull(message = "Name field is mandatory")
  @Length(min = 2, message = "Name should be minimum 2 characters long")
  private String name;

  @NotBlank(message = "Surname field cannot be blank")
  @NotNull(message = "Surname field is mandatory")
  @Length(min = 2, message = "Surname should be minimum 2 characters long")
  private String surname;

  @NotBlank(message = "Username field cannot be blank")
  @NotNull(message = "Username field is mandatory")
  @Length(min = 6, max = 24, message = "Username should be minimum 6 and maximum 24 characters long")
  private String username;

  @NotBlank(message = "Email field cannot be blank")
  @NotNull(message = "Email field is mandatory")
  @Email(message = "Invalid email", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  private String email;

  @NotBlank(message = "Date of birth field cannot be blank")
  @NotNull(message = "Date of birth is mandatory")
  @Past
  private LocalDate dateOfBirth;

  @NotNull(message = "City is mandatory")
  @Min(value = 0, message = "Invalid city id")
  private Integer cityId;

}
