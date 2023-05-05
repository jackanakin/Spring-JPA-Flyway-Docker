package br.kuhn.dev.springboot._core.registration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.kuhn.dev.springboot._common.exception.ResourceConflictException;
import br.kuhn.dev.springboot._core.registration.IRegistrationApi;
import br.kuhn.dev.springboot._core.registration.dto.RegistrationDto;
import br.kuhn.dev.springboot._core.registration.service.RegistrationService;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController implements IRegistrationApi {

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void create(@Valid RegistrationDto dto) throws ResourceConflictException {
        boolean success = registrationService.createUser(dto);

        if (!success) {
            throw new ResourceConflictException("E-mail address already in use");
        }
    }
}
