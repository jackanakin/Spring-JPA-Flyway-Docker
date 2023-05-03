package br.kuhn.dev.springboot._common.repository;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public class GenericPage<T> extends PageImpl<T> {

    public GenericPage(List<T> content) {
        super(content);
    }

    public GenericPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public GenericPage(List<T> content, int pageNumber, int pageSize, Long totalElements) {

        super(content, PageRequest.of(pageNumber, pageSize), totalElements);
    }
}
