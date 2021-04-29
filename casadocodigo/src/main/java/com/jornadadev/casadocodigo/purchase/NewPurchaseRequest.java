package com.jornadadev.casadocodigo.purchase;

import com.jornadadev.casadocodigo.countrystates.Country;
import com.jornadadev.casadocodigo.countrystates.State;
import com.jornadadev.casadocodigo.validation.ExistsId;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

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
  @CPF
  private String cpf;
  @CNPJ
  private String cnpj;
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

  public NewPurchaseRequest(String email, String name, String surname, String cpf, String cnpj,
      String complement, String address, String city, Long countryId, String stateId,
      String phone, String cep) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.cpf = cpf;
    this.cnpj = cnpj;
    this.complement = complement;
    this.address = address;
    this.city = city;
    this.countryId = countryId;
    this.stateId = stateId;
    this.phone = phone;
    this.cep = cep;
  }

}
