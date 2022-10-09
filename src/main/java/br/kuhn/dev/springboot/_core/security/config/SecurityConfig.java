package br.kuhn.dev.springboot._core.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.kuhn.dev.springboot._core.security.entity.User;
import br.kuhn.dev.springboot._core.security.repository.IUserRepository;
import br.kuhn.dev.springboot._core.security.service.JwtTokenAuthenticationFilterService;
import br.kuhn.dev.springboot._core.security.service.JwtTokenProviderService;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http,
            JwtTokenProviderService tokenProvider) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .authorizeRequests(c -> c
                        .antMatchers("/auth/signin").permitAll()
                        .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/foos/**").hasRole("NOBODY")
                        .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtTokenAuthenticationFilterService(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    UserDetailsService customUserDetailsService(IUserRepository users) {
        return (username) -> users.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    @Bean
    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        return authentication -> {
            String username = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";

            User user = (User) userDetailsService.loadUserByUsername(username);

            if (!encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }

            if (!user.isEnabled()) {
                throw new DisabledException("User account is not active");
            }

            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        };
    }
}
