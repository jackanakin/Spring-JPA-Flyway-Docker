package br.kuhn.dev.springboot._common.repository;

import java.util.UUID;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<T> extends PagingAndSortingRepository<T, UUID> {}