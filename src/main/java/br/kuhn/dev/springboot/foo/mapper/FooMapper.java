package br.kuhn.dev.springboot.foo.mapper;

import org.mapstruct.Mapper;

import br.kuhn.dev.springboot._common.mapper.IMapper;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Mapper
public interface FooMapper extends IMapper<Foo, FooDto> {
    @Override
    FooDto entityToDto(Foo entity);

    @Override
    Foo dtoToEntity(FooDto dto);
}
