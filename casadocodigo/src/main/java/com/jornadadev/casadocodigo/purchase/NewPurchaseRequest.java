package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.countrystate.Country;
import com.jornadadev.casadocodigo.countrystate.State;
import com.jornadadev.casadocodigo.coupon.Coupon;
import com.jornadadev.casadocodigo.coupon.CouponRepository;
import com.jornadadev.casadocodigo.order.NewOrderRequest;
import com.jornadadev.casadocodigo.order.Order;
import com.jornadadev.casadocodigo.validation.Document;
import com.jornadadev.casadocodigo.validation.ExistsId;
import java.util.Optional;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

// classe que representa os dados da requisicao, DTO
public class NewPurchaseRequest {

  @NotBlank @Email
  private String email;
  @NotBlank
  private String name;
  @NotBlank
  private String surname;
  @NotBlank
  @Document
  private String document;
  @NotBlank
  private String address;
  @NotBlank
  private String complement;
  @NotBlank
  private String city;
  @NotNull
  @ExistsId(domainClass = Country.class, fieldName = "countryId")
  private Long countryId;
  // lembrar q ID do estado pode ser nulo
  @ExistsId(domainClass = State.class, fieldName = "stateId")
  private Long stateId;
  @NotBlank
  private String phone;
  @NotBlank
  private String cep;
  @NotNull
  @Valid
  // 1
  private NewOrderRequest orderRequest;
  @ExistsId(domainClass = Coupon.class, fieldName = "code")
  private String couponCode;

  public NewPurchaseRequest(@Email @NotBlank String email, @NotBlank String name,
      @NotBlank String surname, @NotBlank String document, @NotBlank String address,
      @NotBlank String complement, @NotBlank String city, @NotNull Long countryId,
      @NotBlank String phone, @NotBlank String cep, @NotNull @Valid NewOrderRequest orderRequest) {
    super();
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.document = document;
    this.address = address;
    this.complement = complement;
    this.city = city;
    this.countryId = countryId;
    this.phone = phone;
    this.cep = cep;
    this.orderRequest = orderRequest;
  }

  public void setStateId(Long stateId) {
    this.stateId = stateId;
  }

  public void setCouponCode(String couponCode) {
    this.couponCode = couponCode;
  }

  public NewOrderRequest getOrderRequest() {
    return orderRequest;
  }

  public String getDocument() {
    return document;
  }

  public Long getCountryId() {
    return countryId;
  }

  public Long getStateId() {
    return stateId;
  }

  @Override
  public String toString() {
    return "NewPurchaseRequest [" +
        "email='" + email + '\'' +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", document='" + document + '\'' +
        ", address='" + address + '\'' +
        ", complement='" + complement + '\'' +
        ", city='" + city + '\'' +
        ", countryId=" + countryId +
        ", stateId=" + stateId +
        ", phone='" + phone + '\'' +
        ", cep='" + cep + '\'' +
        ", orderRequest=" + orderRequest +
        ']';
  }

  public boolean validDocument() {
    Assert.hasLength(document, "you should not validate a document if its not filled out");
    CPFValidator cpfValidator = new CPFValidator();
    cpfValidator.initialize(null);

    CNPJValidator cnpjValidator = new CNPJValidator();
    cnpjValidator.initialize(null);

    return cpfValidator.isValid(document, null)
        || cnpjValidator.isValid(document, null);
  }

  // 1 (repository)
  public Purchase toModel(EntityManager manager, CouponRepository couponRepository) {
    @NotNull
    // 1
    Country country = manager.find(Country.class, countryId);

    // 1 (compra)
    // 1 (pedido)
    Function<Purchase, Order> createOrderFunction = orderRequest.toModel(manager);

    // 1 funcao como argumento (lazy evaluation)
    Purchase purchase = new Purchase(email, name, surname, document, address, complement, country, phone, cep, createOrderFunction);
    // 1
    if (stateId != null) {
      purchase.setState(manager.find(State.class, stateId));
    }
    // 1
    if (StringUtils.hasText(couponCode)) {
      Coupon coupon = couponRepository.getByCode(couponCode);
      purchase.applyCoupon(coupon);
    }
    return purchase;
  }

  public boolean hasState() {
    return Optional.ofNullable(stateId).isPresent();
  }

  public Optional<String> getCouponCode() {
    return Optional.ofNullable(couponCode);
  }

}
