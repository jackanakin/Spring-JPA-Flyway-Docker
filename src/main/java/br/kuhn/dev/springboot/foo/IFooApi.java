package br.kuhn.dev.springboot.foo;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.kuhn.dev.springboot._common.repository.GenericPage;
import br.kuhn.dev.springboot.foo.dto.FooDto;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public interface IFooApi {

    @GetMapping
    GenericPage<FooDto> findAll(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize);

    @GetMapping(value = "/{id}")
    FooDto findById(@PathVariable("id") UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    FooDto create(@RequestBody @Valid FooDto dto);

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    FooDto update(@PathVariable("id") UUID id, @RequestBody @Valid FooDto dto);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable("id") UUID id);
}
