package com.jornadadev.casadocodigo.validation;

import com.jornadadev.casadocodigo.countrystates.Country;
import com.jornadadev.casadocodigo.countrystates.State;
import com.jornadadev.casadocodigo.purchase.NewPurchaseRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class StateBelongsToCountryValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    // garantir que a classe que esta sendo validade é a mesma ou é filha de NewPurchaseRequest
    @Override
    public boolean supports(Class<?> clazz) {
        return NewPurchaseRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NewPurchaseRequest request = (NewPurchaseRequest) target;

        Country country = manager.find(Country.class, request.getCountryId());
        State state = manager.find(State.class, request.getStateId());

        if (!state.belongsToCountry(country)) {
            errors.rejectValue("stateId", null, "this state doens't belong to selected country");

        }

    }
}
