package com.maruf.showcase.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "tb_state")
public class State {

  @Id
  @ColumnDefault("nextval('tb_state_id_seq'::regclass)")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @ManyToOne
  @JoinColumn(name = "country_id", nullable = false)
  private Country country;

}