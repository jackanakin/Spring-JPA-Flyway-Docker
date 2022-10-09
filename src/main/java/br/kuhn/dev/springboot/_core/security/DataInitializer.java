package br.kuhn.dev.springboot._core.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.kuhn.dev.springboot._core.security.entity.User;
import br.kuhn.dev.springboot._core.security.repository.IUserRepository;

import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        long count = this.userRepository.count();

        if (count > 0) {
            log.info("skipping users creation...");
            return;
        }

        log.info("creating users...");

        this.userRepository.save(User.builder()
                .username("user")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER"))
                .build());

        this.userRepository.save(User.builder()
                .username("admin")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build());

        this.userRepository.findAll().forEach(v -> log.info(" User :" + v.getUsername()));
    }
}