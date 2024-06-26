package com.maruf.showcase.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "description", nullable = false, length = 500)
  private String description;

  @Column(name = "rating", nullable = false)
  private Short rating;

  @ManyToOne
  @JoinColumn(name = "author_id", nullable = false)
  private User author;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

}