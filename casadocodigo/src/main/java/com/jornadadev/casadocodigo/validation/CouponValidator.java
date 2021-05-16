package com.jornadadev.casadocodigo.validation;

import com.jornadadev.casadocodigo.coupon.Coupon;
import com.jornadadev.casadocodigo.coupon.CouponRepository;
import com.jornadadev.casadocodigo.purchase.NewPurchaseRequest;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CouponValidator implements Validator {

  private CouponRepository couponRepository;

  public CouponValidator(CouponRepository couponRepository) {
    super();
    this.couponRepository = couponRepository;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return NewPurchaseRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    if (errors.hasErrors()) return;

    NewPurchaseRequest request = (NewPurchaseRequest) target;
    Optional<String> possibleCouponCode = request.getCouponCode();

    if (possibleCouponCode.isPresent()) {
      Coupon coupon = couponRepository.getByCode(possibleCouponCode.get());

      Assert.state(Objects.nonNull(coupon), "At this point of the validation the coupon code must exist");
      if (!coupon.valid()) errors.rejectValue("couponCode", null, "This coupon has expired");
    }
  }

}
