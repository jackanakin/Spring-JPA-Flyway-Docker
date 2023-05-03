package br.kuhn.dev.springboot._common.mapper;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public interface IMapper<E, D> {
    D entityToDto(E entity);

    E dtoToEntity(D dto);
}
