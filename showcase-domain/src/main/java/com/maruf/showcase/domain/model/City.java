package com.maruf.showcase.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "tb_city")
public class City {

  @Id
  @ColumnDefault("nextval('tb_city_id_seq'::regclass)")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "code", nullable = false, length = 2)
  private String code;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @ManyToOne
  @JoinColumn(name = "state_id", nullable = false)
  private State state;

}