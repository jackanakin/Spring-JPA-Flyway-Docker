package br.kuhn.dev.springboot.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.kuhn.dev.springboot.entity.Foo;
import br.kuhn.dev.springboot.service.interfaces.common.IOperations;

public interface IFooService extends IOperations<Foo> {
    
    Page<Foo> findPaginated(Pageable pageable);

}