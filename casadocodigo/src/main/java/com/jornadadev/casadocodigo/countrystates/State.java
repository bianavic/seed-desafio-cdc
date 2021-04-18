package com.jornadadev.casadocodigo.countrystates;

import com.jornadadev.casadocodigo.validation.UniqueValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  public State(String stateName, Country country) {
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
}
