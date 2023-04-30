package br.kuhn.dev.springboot.foo.service.interfaces;

import br.kuhn.dev.springboot._common.service.BaseService;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;

public interface IFooService extends BaseService<Foo, FooDto> {}
