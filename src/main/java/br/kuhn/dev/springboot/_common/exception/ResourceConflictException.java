package br.kuhn.dev.springboot._common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public final class ResourceConflictException extends RuntimeException {

    public ResourceConflictException() {
        super();
    }

    public ResourceConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceConflictException(final String message) {
        super(message);
    }

    public ResourceConflictException(final Throwable cause) {
        super(cause);
    }

}