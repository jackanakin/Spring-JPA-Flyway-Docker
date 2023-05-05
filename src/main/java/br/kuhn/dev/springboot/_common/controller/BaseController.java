package br.kuhn.dev.springboot._common.controller;

import java.util.Optional;

import br.kuhn.dev.springboot._common.exception.ResourceNotFoundException;
import br.kuhn.dev.springboot._common.mapper.IMapper;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public abstract class BaseController<E, D> {
    protected IMapper<E, D> mapper;

    protected BaseController() {
    }

    protected BaseController(IMapper<E, D> mapper) {
        this.mapper = mapper;
    }

    /**
     * Check if some value was found, otherwise throw exception.
     * 
     * @param resource T
     * @return T
     * @throws ResourceNotFoundException
     */
    protected <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }

        return resource;
    }

    /**
     * Check if some value was found, otherwise throw exception.
     * 
     * @param resource Optional<T>
     * @return T
     * @throws ResourceNotFoundException
     */
    protected <T> T checkFound(final Optional<T> resource) {
        if (resource.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return resource.get();
    }
}
