package br.kuhn.dev.springboot.foo.service;

import java.util.List;
import java.util.UUID;

import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.repository.IFooRepository;
import br.kuhn.dev.springboot.foo.service.interfaces.IFooService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;

@Service
@Transactional
public class FooService implements IFooService {

    @Autowired
    private IFooRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Foo findById(final UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Foo> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public Foo create(final Foo entity) {
        return repository.save(entity);
    }

    @Override
    public Foo update(final Foo entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(final Foo entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(final UUID entityId) {
        repository.deleteById(entityId);
    }
}