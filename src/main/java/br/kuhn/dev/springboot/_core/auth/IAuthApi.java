package br.kuhn.dev.springboot._core.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.kuhn.dev.springboot._core.auth.dto.AuthRequestDto;
import br.kuhn.dev.springboot._core.auth.dto.AuthResponseDto;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
public interface IAuthApi {

    @PostMapping()
    ResponseEntity<AuthResponseDto> signin(@RequestBody AuthRequestDto dto);

    @DeleteMapping()
    ResponseEntity<?> logoutUser();
}
