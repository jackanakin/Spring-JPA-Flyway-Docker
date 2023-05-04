package br.kuhn.dev.springboot._common.repository;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import br.kuhn.dev.springboot._common.mapper.IMapper;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Repository
@RequiredArgsConstructor
public class CustomRepository<E, D> {

        private final EntityManager entityManager;

        public GenericPage<D> findPageable(int pageNumber, int pageSize, Class<E> entityClass, IMapper<E, D> mapper) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                CriteriaQuery<Long> countQuery = criteriaBuilder
                                .createQuery(Long.class);
                countQuery.select(criteriaBuilder
                                .count(countQuery.from(entityClass)));

                Long count = entityManager.createQuery(countQuery)
                                .getSingleResult();

                CriteriaQuery<E> criteriaQuery = criteriaBuilder
                                .createQuery(entityClass);

                Root<E> from = criteriaQuery.from(entityClass);

                CriteriaQuery<E> select = criteriaQuery.select(from);

                TypedQuery<E> typedQuery = entityManager.createQuery(select);

                typedQuery.setFirstResult(pageNumber * pageSize);
                typedQuery.setMaxResults(pageSize);

                return new GenericPage<D>(
                                typedQuery.getResultList().stream().map(mapper::entityToDto)
                                                .collect(Collectors.toList()),
                                pageNumber, pageSize, count);
        }
        
}