package br.kuhn.dev.springboot.controller;

import br.kuhn.dev.springboot.entity.Person;
import br.kuhn.dev.springboot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("")
    public Iterable<Person> get() {
        return personRepository.findAll();
    }
}
