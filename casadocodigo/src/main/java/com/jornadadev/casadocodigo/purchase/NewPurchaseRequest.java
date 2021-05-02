package com.jornadadev.casadocodigo.purchase;

import javax.persistence.EntityManager;
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
  private String document;
  @NotBlank
  private String address;
  @NotBlank
  private String complement;
  @NotBlank
  private String city;
  @NotNull
  private Long countryId;
  private Long stateId;
  @NotBlank
  private String phone;
  @NotBlank
  private String cep;

  public NewPurchaseRequest(@Email @NotBlank String email, @NotBlank String name, @NotBlank String surname,
                            @NotBlank String document,
                            @NotBlank String address, @NotBlank String complement,
                            @NotBlank String city, @NotNull Long countryId, Long stateId,
                            @NotBlank String phone, @NotBlank String cep) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.document = document;
    this.address = address;
    this.complement = complement;
    this.city = city;
    this.countryId = countryId;
    this.stateId = stateId;
    this.phone = phone;
    this.cep = cep;
  }

  public Long getCountryId() {
    return countryId;
  }

  public Long getStateId() {
    return stateId;
  }

  /**
  public Purchase toModel(EntityManager manager) {

    return new Purchase(this.email, this.name, this.surname, this.document, this.complement, this.address, this.city, this.countryId, this.stateId, this.phone, this.cep);
  }
**/
}
