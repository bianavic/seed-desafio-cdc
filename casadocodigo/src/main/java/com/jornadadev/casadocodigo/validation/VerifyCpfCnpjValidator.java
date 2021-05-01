package com.jornadadev.casadocodigo.validation;

import com.jornadadev.casadocodigo.purchase.NewPurchaseRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerifyCpfCnpjValidator implements Validator {
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
        if (!request.cpfOrCnpjIsValid()) {
            errors.rejectValue("document", null, "document must be CPF or CNPJ");
        }

    }
}
