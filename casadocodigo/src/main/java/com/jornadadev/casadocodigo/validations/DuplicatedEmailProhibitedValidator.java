package com.jornadadev.casadocodigo.validations;

import com.jornadadev.casadocodigo.models.Author;
import com.jornadadev.casadocodigo.models.NewAuthorRequest;
import com.jornadadev.casadocodigo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class DuplicatedEmailProhibitedValidator implements Validator {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     *
     * @param clazz
     * @return
     * Sobre supports:
    qual é o tipo de parametro que a gente deve aplicar nessa validacao
    verificar se a classe que esta chegando como argumento é a mesma ou
    é filha do NewAuthorRequest
    invoca na classe que é a mae, passando uma outra classe como argumento
    as classes podem ser iguais ou filha.
     */

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAuthorRequest.class.isAssignableFrom(clazz);
    }

    /**
     *
     * @param clazz
     * @return
     * sabendo que é suportada aquela classe, podemos trabalhar no parametro (formulario).
     * antes garantimos que o validador só roda caso nao haja erro de validacao até aquele momento,
     * pois o Spring aplica os erros de validacao antes do meu
     */
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
