package com.jornadadev.casadocodigo.countrystates;

import com.jornadadev.casadocodigo.validation.ExistsId;
import com.jornadadev.casadocodigo.validation.UniqueValue;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewStateRequest {

  @NotBlank
  @UniqueValue(domainClass = State.class, fieldName = "stateName")
  private String stateName;

  @NotNull
  @ExistsId(domainClass = Country.class, fieldName = "id")
  private Long countryId;

  public NewStateRequest(String stateName, Long countryId) {
    this.stateName = stateName;
    this.countryId = countryId;
  }

  @Override
  public String toString() {
    return "NewStateRequest{" +
        "stateName='" + stateName + '\'' +
        ", countryId=" + countryId +
        '}';
  }

  public State toModel(EntityManager manager) {
    return new State(stateName, manager.find(Country.class, countryId));
  }

}
