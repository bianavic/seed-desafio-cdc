package com.jornadadev.casadocodigo.coupon;

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
import org.springframework.util.Assert;

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

  public Coupon(String code, BigDecimal discountPercentage, LocalDate expiresAt) {
    Assert.isTrue(
        expiresAt.compareTo(LocalDate.now()) >= 0, "expiration date must be in the future"
    );
    this.code = code;
    this.discountPercentage = discountPercentage;
    this.expiresAt = expiresAt;
  }

  public boolean valid() {
    return LocalDate.now().compareTo(this.expiresAt) <= 0;
  }

  public BigDecimal getDiscountPercentage() {
    return discountPercentage;
  }

  public LocalDate getExpiresAt() {
    return expiresAt;
  }

  @Override
  public String toString() {
    return "Coupon{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", discountPercentage=" + discountPercentage +
        ", expiresAt=" + expiresAt +
        '}';
  }
}
