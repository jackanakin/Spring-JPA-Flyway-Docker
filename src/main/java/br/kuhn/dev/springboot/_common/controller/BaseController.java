package br.kuhn.dev.springboot._common.controller;

import org.springframework.web.bind.annotation.RestController;

import br.kuhn.dev.springboot._common.exception.ResourceNotFoundException;

@RestController
public abstract class BaseController {

    /**
     * Check if some value was found, otherwise throw exception.
     * 
     * @param resource T
     * @throws ResourceNotFoundException
     */
    public <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }

        return resource;
    }

}
