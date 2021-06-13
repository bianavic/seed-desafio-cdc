package com.jornadadev.casadocodigo.coupon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.jornadadev.casadocodigo.validation.UniqueValue;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

// classe que representa os dados da requisicao, DTO
public class NewCouponRequest {

  @NotBlank
  @UniqueValue(domainClass = Coupon.class, fieldName = "code")
  private String code;
  @Positive
  @NotNull
  private BigDecimal discountPercentage;
  @NotNull @Future
  @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
  private LocalDate expiresAt;

  public NewCouponRequest(String code, BigDecimal discountPercentage) {
    super();
    this.code = code;
    this.discountPercentage = discountPercentage;
  }

  public String getCode() {
    return code;
  }

  public void setExpiresAt(LocalDate expiresAt) {
    this.expiresAt = expiresAt;
  }

  Coupon toModel() {
    return new Coupon(code, discountPercentage, expiresAt);
  }

}
