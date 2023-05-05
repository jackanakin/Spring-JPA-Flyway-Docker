package br.kuhn.dev.springboot.foo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.kuhn.dev.springboot._common.controller.BaseController;
import br.kuhn.dev.springboot._common.repository.GenericPage;
import br.kuhn.dev.springboot.foo.IFooApi;
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
class FooController extends BaseController<Foo, FooDto> implements IFooApi {

    @Autowired
    private IFooService service;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 5;

    @Autowired
    public FooController(FooMapper fooMapper) {
        super(fooMapper);
    }

    @Override
    public GenericPage<FooDto> findAll(Integer pageNumber,
            Integer pageSize) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return service.findPageable(pageNumber, pageSize, this.mapper);
    }

    @Override
    public FooDto findById(UUID id) {
        return this.mapper.entityToDto(checkFound(service.findById(id)));
    }

    @Override
    public FooDto create(FooDto dto) {
        Foo result = service.create(this.mapper.dtoToEntity(dto));

        return this.mapper.entityToDto(result);
    }

    @Override
    public FooDto update(UUID id, FooDto dto) {
        return this.mapper.entityToDto(service.update(dto, id));
    }

    @Override
    public void delete(UUID id) {
        service.deleteById(id);
    }

}