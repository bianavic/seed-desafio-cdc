package com.jornadadev.casadocodigo.countrystates;

import com.jornadadev.casadocodigo.validation.UniqueValue;
import javax.validation.constraints.NotBlank;

public class NewCountryRequest {

  @NotBlank @UniqueValue(domainClass = Country.class, fieldName = "countryName")
  private String countryName;

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }
}
