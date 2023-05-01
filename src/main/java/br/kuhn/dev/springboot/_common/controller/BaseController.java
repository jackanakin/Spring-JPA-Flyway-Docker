package br.kuhn.dev.springboot._common.controller;

import java.util.Optional;
import java.util.function.Function;

import br.kuhn.dev.springboot._common.exception.ResourceNotFoundException;

public abstract class BaseController<E, D> {

    protected Function<E, D> entityToDto;
    protected Function<D, E> dtoToEntity;

    public BaseController(Function<E, D> entityToDto, Function<D, E> dtoToEntity) {
        this.entityToDto = entityToDto;
        this.dtoToEntity = dtoToEntity;
    }

    /**
     * Check if some value was found, otherwise throw exception.
     * 
     * @param resource T
     * @return T
     * @throws ResourceNotFoundException
     */
    public <T> T checkFound(final T resource) {
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
    public <T> T checkFound(final Optional<T> resource) {
        if (resource.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return resource.get();
    }
}
