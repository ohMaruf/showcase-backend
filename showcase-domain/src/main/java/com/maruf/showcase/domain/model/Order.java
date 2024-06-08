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
@Table(name = "tb_order")
public class Order {

  @Id
  @ColumnDefault("nextval('tb_order_id_seq'::regclass)")
  @Column(name = "id", nullable = false)
  private Integer id;

  @ColumnDefault("1")
  @Column(name = "quantity", nullable = false)
  private Short quantity;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

}