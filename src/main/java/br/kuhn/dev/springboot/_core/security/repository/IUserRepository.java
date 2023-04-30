package br.kuhn.dev.springboot._core.security.repository;

import br.kuhn.dev.springboot._common.repository.BaseRepository;
import br.kuhn.dev.springboot._core.security.entity.User;

import java.util.Optional;

public interface IUserRepository extends BaseRepository<User> {
    
    Optional<User> findByUsername(String username);
    
}
