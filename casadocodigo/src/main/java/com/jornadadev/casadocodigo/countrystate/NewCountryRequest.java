package com.jornadadev.casadocodigo.countrystate;

import com.jornadadev.casadocodigo.validation.UniqueValue;
import javax.validation.constraints.NotBlank;

// classe que representa os dados da requisicao, DTO
public class NewCountryRequest {

  @NotBlank
  @UniqueValue(domainClass = Country.class, fieldName = "countryName")
  private String countryName;

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }
}
