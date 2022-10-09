package br.kuhn.dev.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.kuhn.dev.springboot.entity.User;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    
}
