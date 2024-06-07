package com.maruf.showcase.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_country", indexes = {
    @Index(name = "tb_country_pkey", columnList = "id"),
    @Index(name = "tb_country_code_key", columnList = "code"),
    @Index(name = "tb_country_name_key", columnList = "name")
})
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private Long id;

  @Column(name = "code", length = 2, nullable = false, unique = true)
  @NotNull
  private String code;

  @Column(name = "name", length = 50, nullable = false, unique = true)
  @NotNull
  private String name;

  public Country() {
  }

  public @NotNull Long getId() {
    return id;
  }

  public void setId(@NotNull Long id) {
    this.id = id;
  }

  public @NotNull String getCode() {
    return code;
  }

  public void setCode(@NotNull String code) {
    this.code = code;
  }

  public @NotNull String getName() {
    return name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }
}
