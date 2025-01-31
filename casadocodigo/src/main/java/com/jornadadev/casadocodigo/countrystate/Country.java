package com.jornadadev.casadocodigo.countrystate;

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

    // cuidado considerar id(informacao nao natural) no equals - senao diz que 2 objetos sao iguais se os ids forem nulos.
    // considere criterio de igualdade pela informacao q é natural e obrigatoria
    Country other = (Country) o;
    if (countryName == null) {
      if (other.countryName != null)
        return false;
    } else if (!countryName.equals(other.countryName))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(countryId, countryName);
  }
}
