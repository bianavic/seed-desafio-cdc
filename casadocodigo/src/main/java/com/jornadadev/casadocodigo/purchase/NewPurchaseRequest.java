package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.countrystates.Country;
import com.jornadadev.casadocodigo.countrystates.State;
import com.jornadadev.casadocodigo.validation.ExistsId;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

/**
 * documento(cpf/cnpj) obrigatório e só precisa ser um cpf ou cnpj
 * se o país tiver estados, um estado precisa ser selecionado
 * estado(caso aquele pais tenha estado) - apenas se o país tiver cadastro de estados
 */


public class NewPurchaseRequest {

  @NotBlank @Email
  private String email;
  @NotBlank
  private String name;
  @NotBlank
  private String surname;
  @NotBlank
  private String cpfOrCnpj;
  @NotBlank
  private String complement;
  @NotBlank
  private String address;
  @NotBlank
  private String city;
  @NotNull @ExistsId(domainClass = Country.class, fieldName = "countryId")
  private Long countryId;
  @ExistsId(domainClass = State.class, fieldName = "stateId")
  private String stateId;
  @NotBlank
  private String phone;
  @NotBlank
  private String cep;

  public NewPurchaseRequest(String email, String name, String surname, String cpfOrCnpj, String complement,
                            String address, String city, Long countryId, String stateId, String phone, String cep) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.cpfOrCnpj = cpfOrCnpj;
    this.complement = complement;
    this.address = address;
    this.city = city;
    this.countryId = countryId;
    this.stateId = stateId;
    this.phone = phone;
    this.cep = cep;
  }

  public String getCpfOrCnpj() {
    return cpfOrCnpj;
  }

  public boolean cpfOrCnpjIsValid() {
    Assert.hasLength(cpfOrCnpj, "you shouldn't have to validate this document if it wasn't filled"); // bug

    CPFValidator cpfValidator = new CPFValidator();
    cpfValidator.initialize(null); // ant pattern

    CNPJValidator cnpjValidator = new CNPJValidator();
    cnpjValidator.initialize(null); // ant pattern

    return cpfValidator.isValid(cpfOrCnpj, null) || cnpjValidator.isValid(cpfOrCnpj, null);
  }

  public Long getCountryId() {
    return countryId;
  }

  public String getStateId() {
    return stateId;
  }

  @Override
  public String toString() {
    return "NewPurchaseRequest{" +
            "email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", cpfOrCnpj='" + cpfOrCnpj + '\'' +
            ", complement='" + complement + '\'' +
            ", address='" + address + '\'' +
            ", city='" + city + '\'' +
            ", countryId=" + countryId +
            ", stateId='" + stateId + '\'' +
            ", phone='" + phone + '\'' +
            ", cep='" + cep + '\'' +
            '}';
  }
}
