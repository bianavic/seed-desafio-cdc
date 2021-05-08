package com.jornadadev.casadocodigo.cupom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.jornadadev.casadocodigo.validation.UniqueValue;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NewCouponRequest {

  @NotBlank
  @UniqueValue(domainClass = Coupon.class, fieldName = "code")
  private String code;
  @Positive
  @NotNull
  private BigDecimal discountPercentage;
  @NotNull @Future
  @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
  private LocalDate couponValidity;

  public void setCouponValidity(LocalDate couponValidity) {
    this.couponValidity = couponValidity;
  }

  public NewCouponRequest(String code, BigDecimal discountPercentage) {
    super();
    this.code = code;
    this.discountPercentage = discountPercentage;
  }

  Coupon toModel() {
    return new Coupon(code, discountPercentage, couponValidity);
  }
}
