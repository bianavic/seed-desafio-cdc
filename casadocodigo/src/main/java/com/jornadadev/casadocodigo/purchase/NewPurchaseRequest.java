package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.countrystate.Country;
import com.jornadadev.casadocodigo.countrystate.State;
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
  private NewOrderRequest orderRequest;

  public NewPurchaseRequest(@Email @NotBlank String email, @NotBlank String name, @NotBlank String surname,
      @NotBlank String document, @NotBlank String address, @NotBlank String complement,
      @NotBlank String city, @NotNull Long countryId, @NotBlank String phone, @NotBlank String cep, @NotNull NewOrderRequest orderRequest) {
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

  public Long getCountryId() {
    return countryId;
  }

  public Long getStateId() {
    return stateId;
  }

  @Override
  public String toString() {
    return "NewPurchaseRequest{" +
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
        '}';
  }

  public Purchase toModel(EntityManager manager) {
    @NotNull
    Country country = manager.find(Country.class, countryId);

    Function<Purchase, Order> createOrderFunction = orderRequest.toModel(manager);

    // funcao como argumento (lazy evaluation)
    Purchase purchase = new Purchase(email, name, surname, document, address, complement, country, phone, cep, createOrderFunction);

    if (stateId != null) {
      purchase.setState(manager.find(State.class, stateId));
    }
    return purchase;
  }

  public boolean hasState() {
    return Optional.ofNullable(stateId).isPresent();
  }

}
