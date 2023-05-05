package br.kuhn.dev.springboot._core.registration.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.kuhn.dev.springboot._core.registration.dto.RegistrationDto;
import br.kuhn.dev.springboot._core.user.entity.User;
import br.kuhn.dev.springboot._core.user.repository.IUserRepository;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Service
public class RegistrationService implements IRegistrationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(RegistrationDto dto) {

        Optional<User> foundByUsername = userRepository.findByUsername(dto.getEmail());

        if ( foundByUsername.isPresent() )
        {
            return false;
        }

        userRepository.save(User.builder()
                .username(dto.getEmail())
                .password(this.passwordEncoder.encode(dto.getPassword()))
                .roles(Arrays.asList("ROLE_USER"))
                .build());

        return true;
    }

}
