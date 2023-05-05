package br.kuhn.dev.springboot._core.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.kuhn.dev.springboot._common.controller.BaseController;
import br.kuhn.dev.springboot._core.auth.IAuthApi;
import br.kuhn.dev.springboot._core.auth.dto.AuthRequestDto;
import br.kuhn.dev.springboot._core.auth.dto.AuthResponseDto;
import br.kuhn.dev.springboot._core.auth.service.AuthService;
import br.kuhn.dev.springboot._core.user.entity.User;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController<User, AuthRequestDto> implements IAuthApi {
    
    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<AuthResponseDto> signin(AuthRequestDto dto) {

        try {
            String username = dto.getUsername();
            String password = dto.getPassword();

            String token = authService.authenticate(username, password);

            AuthResponseDto response = new AuthResponseDto(username, token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @Override
    public ResponseEntity<?> logoutUser() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Long userId = userDetails.getId();
        // refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok("okok");
    }
}