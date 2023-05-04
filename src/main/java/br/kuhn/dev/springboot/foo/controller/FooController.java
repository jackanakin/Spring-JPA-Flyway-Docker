package br.kuhn.dev.springboot.foo.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.kuhn.dev.springboot._common.controller.BaseController;
import br.kuhn.dev.springboot._common.repository.GenericPage;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.mapper.FooMapper;
import br.kuhn.dev.springboot.foo.service.IFooService;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@RestController
@RequestMapping("/foo")
class FooController extends BaseController<Foo, FooDto> {

    @Autowired
    private IFooService service;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 5;

    @Autowired
    public FooController(FooMapper fooMapper) {
        super(fooMapper);
    }

    @GetMapping
    public GenericPage<FooDto> findAll(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return service.findPageable(pageNumber, pageSize, this.mapper);
    }

    @GetMapping(value = "/{id}")
    public FooDto findById(@PathVariable("id") UUID id) {
        return this.mapper.entityToDto(checkFound(service.findById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FooDto create(@RequestBody @Valid FooDto dto) {
        Foo result = service.create(this.mapper.dtoToEntity(dto));

        return this.mapper.entityToDto(result);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FooDto update(@PathVariable("id") UUID id, @RequestBody @Valid FooDto dto) {
        return this.mapper.entityToDto(service.update(dto, id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

}