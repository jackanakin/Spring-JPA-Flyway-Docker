package br.kuhn.dev.springboot.foo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.kuhn.dev.springboot.foo.entity.Foo;

public interface IFooRepository extends JpaRepository<Foo, Long> {

}