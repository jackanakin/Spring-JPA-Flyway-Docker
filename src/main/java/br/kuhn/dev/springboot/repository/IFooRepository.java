package br.kuhn.dev.springboot.repository;


import br.kuhn.dev.springboot.entity.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFooRepository extends JpaRepository<Foo, Long> {

}