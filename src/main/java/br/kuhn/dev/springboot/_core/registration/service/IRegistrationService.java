package br.kuhn.dev.springboot._core.registration.service;

import br.kuhn.dev.springboot._core.registration.dto.RegistrationDto;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public interface IRegistrationService {

    boolean createUser(RegistrationDto dto);

}
