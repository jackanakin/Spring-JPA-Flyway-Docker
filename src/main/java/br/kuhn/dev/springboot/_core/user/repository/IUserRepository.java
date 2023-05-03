package br.kuhn.dev.springboot._core.user.repository;

import br.kuhn.dev.springboot._common.repository.BaseRepository;
import br.kuhn.dev.springboot._core.user.entity.User;

import java.util.Optional;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public interface IUserRepository extends BaseRepository<User> {
    
    Optional<User> findByUsername(String username);
    
}
