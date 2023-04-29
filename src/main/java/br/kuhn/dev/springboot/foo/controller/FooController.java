package br.kuhn.dev.springboot.foo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;

import org.springframework.http.HttpStatus;

import br.kuhn.dev.springboot._common.controller.BaseController;
import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.service.interfaces.IFooService;

@RequestMapping("/foos")
class FooController extends BaseController {

    @Autowired
    private IFooService service;

    @GetMapping
    public List<Foo> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Foo findById(@PathVariable("id") UUID id) {
        return super.checkFound(service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody Foo resource) {
        Preconditions.checkNotNull(resource);
        return service.create(resource).getId();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody Foo resource) {
        Preconditions.checkNotNull(resource);
        
        checkFound(service.findById(resource.getId()));
        
        service.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

}