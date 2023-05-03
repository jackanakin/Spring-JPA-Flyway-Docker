package br.kuhn.dev.springboot._common.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, UUID> {}