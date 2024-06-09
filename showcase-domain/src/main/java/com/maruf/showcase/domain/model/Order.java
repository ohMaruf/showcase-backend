package com.maruf.showcase.domain.model;

import com.maruf.showcase.domain.converters.OrderStatusConverter;
import com.maruf.showcase.domain.enumerations.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_order_id_gen")
  @SequenceGenerator(name = "tb_order_id_gen", sequenceName = "tb_order_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ColumnDefault("1")
  @Column(name = "quantity", nullable = false)
  private Short quantity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Convert(converter = OrderStatusConverter.class)
  @Column(name = "status", nullable = false, columnDefinition = "enum_order_status")
  private OrderStatus status;

}