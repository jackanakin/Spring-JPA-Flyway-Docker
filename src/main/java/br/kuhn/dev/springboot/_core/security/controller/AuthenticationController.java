package br.kuhn.dev.springboot._core.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.kuhn.dev.springboot._core.security.dto.request.AuthenticationRequestDTO;
import br.kuhn.dev.springboot._core.security.entity.User;
import br.kuhn.dev.springboot._core.security.service.JwtTokenProviderService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProviderService jwtTokenProviderService;

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody AuthenticationRequestDTO data) {

        try {
            String username = data.getUsername();
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProviderService.createToken(authentication);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Long userId = userDetails.getId();
        // refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok("okok");
    }
}