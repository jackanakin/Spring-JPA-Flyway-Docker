package br.kuhn.dev.springboot.foo.service.interfaces;

import java.util.List;

import br.kuhn.dev.springboot.foo.entity.Foo;

public interface IFooService {
    public List<Foo> findAll();

    public Foo create(final Foo entity);

    public Foo update(final Foo entity);

    public void delete(final Foo entity);

    public void deleteById(final long entityId);

    public Foo findById(final long id);
}
