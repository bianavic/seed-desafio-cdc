package com.jornadadev.casadocodigo.cupom;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class CouponApplied {

  @ManyToOne
  private Coupon cupom;
  @Positive @NotNull
  private BigDecimal discountPercentage;
  @Future @NotNull
  private LocalDate expiresAt;

  CouponApplied() {}

  public CouponApplied(Coupon cupom, BigDecimal discountPercentage,
      LocalDate expiresAt) {
    this.cupom = cupom;
    this.discountPercentage = discountPercentage;
    this.expiresAt = expiresAt;
  }

  @Override
  public String toString() {
    return "CouponApplied{" +
        "cupom=" + cupom +
        ", discountPercentage=" + discountPercentage +
        ", couponValidity=" + expiresAt +
        '}';
  }
}
