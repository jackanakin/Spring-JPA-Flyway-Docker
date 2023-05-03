package br.kuhn.dev.springboot._core.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.kuhn.dev.springboot._common.controller.BaseController;
import br.kuhn.dev.springboot._core.auth.dto.AuthRequestDto;
import br.kuhn.dev.springboot._core.auth.service.AuthService;
import br.kuhn.dev.springboot._core.user.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController<User, AuthRequestDto> {

    public AuthController() {
        super();
    }

    @Autowired
    private AuthService authService;

    @PostMapping()
    public ResponseEntity<Object> signin(@RequestBody AuthRequestDto dto) {

        try {
            String username = dto.getUsername();
            String password = dto.getPassword();

            String token = authService.authenticate(username, password);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> logoutUser() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Long userId = userDetails.getId();
        // refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok("okok");
    }
}