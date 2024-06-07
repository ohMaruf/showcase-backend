package com.maruf.showcase.domain.model;

import lombok.Data;

@Data
public class State {
  private Integer id;
  private String name;
  private Country country;
}
