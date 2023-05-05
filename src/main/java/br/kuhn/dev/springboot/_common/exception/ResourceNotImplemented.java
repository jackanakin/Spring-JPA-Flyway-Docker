package br.kuhn.dev.springboot._common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public final class ResourceNotImplemented extends RuntimeException {

    public ResourceNotImplemented() {
        super();
    }

    public ResourceNotImplemented(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotImplemented(final String message) {
        super(message);
    }

    public ResourceNotImplemented(final Throwable cause) {
        super(cause);
    }

}