package com.jornadadev.casadocodigo.countrystates;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Country {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long countryId;
  @NotBlank
  private String countryName;

  @Deprecated
  public Country() {}

  public Country(String countryName) {
    this.countryName = countryName;
  }

  @Override
  public String toString() {
    return "Country{" +
        "countryId=" + countryId +
        ", countryName='" + countryName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Country country = (Country) o;
    return Objects.equals(countryId, country.countryId) && Objects
        .equals(countryName, country.countryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countryId, countryName);
  }
}
