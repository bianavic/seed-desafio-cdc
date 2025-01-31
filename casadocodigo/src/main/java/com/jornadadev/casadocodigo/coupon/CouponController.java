package com.jornadadev.casadocodigo.coupon;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

  @PersistenceContext
  private EntityManager manager;

  @PostMapping(value = "/coupons")
  @Transactional
  public String createCoupon(@RequestBody @Valid NewCouponRequest newCouponRequest) {

    Coupon newCoupon = newCouponRequest.toModel();
    manager.persist(newCoupon);

    return newCoupon.toString();
  }

}
