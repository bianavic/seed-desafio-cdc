package com.jornadadev.casadocodigo.coupon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {

  /**
   * Busca por um cupom que existe no sistema
   * @param code
   * @return
   */
  public Coupon getByCode(String code);

}
