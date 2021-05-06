package com.jornadadev.casadocodigo.countrystate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class State {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long stateId;
  @NotBlank
  private String stateName;
  @ManyToOne @NotNull @Valid
  private Country country;

  @Deprecated
  public State() {}

  public State(String stateName, @ Valid Country country) {
    this.stateName = stateName;
    this.country = country;
  }

  @Override
  public String toString() {
    return "State{" +
        "stateId=" + stateId +
        ", stateName='" + stateName + '\'' +
        ", country=" + country +
        '}';
  }

  public boolean belongsToCountry(Country country) {
    return this.country.equals(country);
  }
}
