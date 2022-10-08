package br.kuhn.dev.springboot.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.kuhn.dev.springboot.entity.Foo;

public interface IFooService extends _IOperations<Foo> {
    
    Page<Foo> findPaginated(Pageable pageable);

}