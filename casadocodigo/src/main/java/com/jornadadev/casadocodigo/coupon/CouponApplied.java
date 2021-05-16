package com.jornadadev.casadocodigo.coupon;

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
  private Coupon coupon;
  @Positive @NotNull
  private BigDecimal discountPercentage;
  @Future @NotNull
  private LocalDate validatedAt;

  @Deprecated
  public CouponApplied() {}

  public CouponApplied(Coupon coupon) {
    this.coupon = coupon;
    this.discountPercentage = discountPercentage;
    this.validatedAt = validatedAt;
  }

  @Override
  public String toString() {
    return "CouponApplied[" +
        "coupon=" + coupon +
        ", discountPercentage=" + discountPercentage +
        ", validatedAt=" + validatedAt +
        ']';
  }

}
