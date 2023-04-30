package br.kuhn.dev.springboot.foo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.kuhn.dev.springboot._common.controller.BaseController;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.mapper.FooMapper;
import br.kuhn.dev.springboot.foo.service.interfaces.IFooService;

@RestController
@RequestMapping("/foo")
class FooController extends BaseController {

    @Autowired
    private IFooService service;

    @Autowired
    private FooMapper fooMapper;

    @GetMapping
    public List<Foo> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public FooDto findById(@PathVariable("id") UUID id) {
        Optional<Foo> entity = service.findById(id);

        return fooMapper.FooToFooDto(checkFound(entity));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FooDto create(@RequestBody @Valid FooDto dto) {
        return fooMapper.FooToFooDto(service.create(fooMapper.FooDtoToFoo(dto)));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FooDto update(@PathVariable("id") UUID id, @RequestBody @Valid FooDto dto) {        
        return fooMapper.FooToFooDto(service.update(dto, id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

}