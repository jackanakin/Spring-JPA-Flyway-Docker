package br.kuhn.dev.springboot.foo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.kuhn.dev.springboot._common.exception.ResourceNotFoundException;
import br.kuhn.dev.springboot._common.mapper.IMapper;
import br.kuhn.dev.springboot._common.repository.CustomRepository;
import br.kuhn.dev.springboot._common.repository.GenericPage;
import br.kuhn.dev.springboot.foo.dto.FooDto;
import br.kuhn.dev.springboot.foo.entity.Foo;
import br.kuhn.dev.springboot.foo.repository.IFooRepository;
import br.kuhn.dev.springboot.foo.service.interfaces.IFooService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Service
@Transactional
public class FooService implements IFooService {

    @Autowired
    private IFooRepository repository;

    // @Autowired
    // private CustomFooRepository customRepository;

    @Autowired
    private CustomRepository<Foo, FooDto> customRepository;

    @Override
    public GenericPage<FooDto> findPageable(int pageNumber, int pageSize, IMapper<Foo, FooDto> mapper) {
        return customRepository.findPageable(pageNumber, pageSize, Foo.class, mapper);
        // return customRepository.findPageable(pageNumber, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Foo> findById(final UUID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Foo> findAll() {
        return repository.findAll();
    }

    @Override
    public Foo create(final Foo entity) {
        return repository.save(entity);
    }

    @Override
    public Foo update(final FooDto dto, final UUID id) {
        Foo entity = repository.findById(id).orElseThrow(ResourceNotFoundException::new);

        entity.setName(dto.getName());
        entity.setType(dto.getType());

        return repository.save(entity);
    }

    @Override
    public void delete(final Foo entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(final UUID id) {
        Foo entity = repository.findById(id).orElseThrow(ResourceNotFoundException::new);

        repository.delete(entity);
    }
}