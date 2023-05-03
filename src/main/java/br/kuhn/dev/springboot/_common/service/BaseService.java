package br.kuhn.dev.springboot._common.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.kuhn.dev.springboot._common.mapper.IMapper;
import br.kuhn.dev.springboot._common.repository.GenericPage;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public interface BaseService<E, D> {
    GenericPage<D> findPageable(int pageNumber, int pageSize, IMapper<E, D> mapper);

    List<E> findAll();

    E create(final E entity);

    E update(final D dto, final UUID id);

    void delete(final E entity);

    void deleteById(final UUID id);

    Optional<E> findById(final UUID id);
}
