package br.kuhn.dev.springboot._common.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T, D> {
    List<T> findAll();

    T create(final T entity);

    T update(final D dto, final UUID id);

    void delete(final T entity);

    void deleteById(final UUID id);

    Optional<T> findById(final UUID id);
}
