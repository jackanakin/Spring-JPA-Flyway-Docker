package br.kuhn.dev.springboot.service;

import java.util.List;

import br.kuhn.dev.springboot.repository.IFooRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;

import br.kuhn.dev.springboot.entity.Foo;
import br.kuhn.dev.springboot.service.interfaces.IFooService;
import br.kuhn.dev.springboot.service.interfaces.common.AbstractService;

@Service
@Transactional
public class FooService extends AbstractService<Foo> implements IFooService {

    @Autowired
    private IFooRepository dao;

    public FooService() {
        super();
    }

    // API

    @Override
    protected PagingAndSortingRepository<Foo, Long> getRepository() {
        return dao;
    }

    // custom methods

    @Override
    public Page<Foo> findPaginated(Pageable pageable) {
        return dao.findAll(pageable);
    }

    // overridden to be secured

    @Override
    @Transactional(readOnly = true)
    public List<Foo> findAll() {
        return Lists.newArrayList(getRepository().findAll());
    }

}