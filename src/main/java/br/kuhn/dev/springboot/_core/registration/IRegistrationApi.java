package br.kuhn.dev.springboot._core.registration;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.kuhn.dev.springboot._common.exception.ResourceConflictException;
import br.kuhn.dev.springboot._core.registration.dto.RegistrationDto;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public interface IRegistrationApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody @Valid RegistrationDto dto) throws ResourceConflictException;

}
