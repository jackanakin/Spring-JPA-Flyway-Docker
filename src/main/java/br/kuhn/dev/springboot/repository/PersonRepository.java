package br.kuhn.dev.springboot.repository;

import br.kuhn.dev.springboot.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
