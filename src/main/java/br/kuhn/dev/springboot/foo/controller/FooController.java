package br.kuhn.dev.springboot.foo.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
class FooController extends BaseController<Foo, FooDto> {

    @Autowired
    private IFooService service;

    @Autowired
    public FooController(FooMapper fooMapper) {
        super((entity) -> fooMapper.FooToFooDto(entity), (dto) -> fooMapper.FooDtoToFoo(dto));
    }

    @GetMapping
    public List<FooDto> findAll() {
        return service.findAll().stream().map(entityToDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public FooDto findById(@PathVariable("id") UUID id) {
        return entityToDto.apply(checkFound(service.findById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FooDto create(@RequestBody @Valid FooDto dto) {
        Foo result = service.create(dtoToEntity.apply(dto));

        return entityToDto.apply(result);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FooDto update(@PathVariable("id") UUID id, @RequestBody @Valid FooDto dto) {
        return entityToDto.apply(service.update(dto, id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

}