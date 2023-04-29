package br.kuhn.dev.springboot._common.service;

import java.util.List;
import java.util.UUID;

public interface BaseService<T> {
    public List<T> findAll();

    public T create(final T entity);

    public T update(final T entity);

    public void delete(final T entity);

    public void deleteById(final UUID id);

    public T findById(final UUID id);
}
