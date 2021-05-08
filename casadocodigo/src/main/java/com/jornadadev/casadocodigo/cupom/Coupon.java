package com.jornadadev.casadocodigo.cupom;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Coupon {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank
  private String code;
  @NotNull @Positive
  private BigDecimal discountPercentage;
  @NotNull @Future
  private LocalDate expiresAt;

  @Deprecated
  Coupon() {}

  public BigDecimal getDiscountPercentage() {
    return discountPercentage;
  }

  public LocalDate getExpiresAt() {
    return expiresAt;
  }

  public Coupon(String code, BigDecimal discountPercentage, LocalDate expiresAt) {
    this.code = code;
    this.discountPercentage = discountPercentage;
    this.expiresAt = expiresAt;
  }

  @Override
  public String toString() {
    return "Coupon{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", discountPercentage=" + discountPercentage +
        ", couponValidity=" + expiresAt +
        '}';
  }
}
