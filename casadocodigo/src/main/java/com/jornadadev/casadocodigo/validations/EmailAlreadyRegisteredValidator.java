package com.jornadadev.casadocodigo.validations;

import com.jornadadev.casadocodigo.author.Author;
import com.jornadadev.casadocodigo.author.NewAuthorRequest;
import com.jornadadev.casadocodigo.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailAlreadyRegisteredValidator implements Validator {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAuthorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        NewAuthorRequest request = (NewAuthorRequest) target;
        Optional<Author> possibleAuthor = authorRepository.findByEmail((String) request.getEmail());

        if (possibleAuthor.isPresent()) {
            errors.reject("email", null, "This email is already registered "
                + request.getEmail());
        }
    }
}
