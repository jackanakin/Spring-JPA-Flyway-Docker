package br.kuhn.dev.springboot.foo.mapper;

import org.mapstruct.Mapper;

import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;

@Mapper
public interface FooMapper {
    FooDto FooToFooDto(Foo foo);

    Foo FooDtoToFoo(FooDto foo);
}
