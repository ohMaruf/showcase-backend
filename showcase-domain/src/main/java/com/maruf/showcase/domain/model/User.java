package com.maruf.showcase.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {

  @Id
  @ColumnDefault("nextval('tb_user_id_seq'::regclass)")
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "surname", nullable = false, length = 50)
  private String surname;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "email", nullable = false, length = 60)
  private String email;

  @Column(name = "username", nullable = false, length = 15, unique = true)
  private String username;

  @ManyToOne
  @JoinColumn(name = "city_id", nullable = false)
  private City city;

  public String getFullName() {
    return name + " " + surname;
  }
}