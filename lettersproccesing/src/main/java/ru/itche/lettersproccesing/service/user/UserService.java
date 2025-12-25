package ru.itche.lettersproccesing.service.auth;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itche.lettersproccesing.dto.user.CreateElfRequest;
import ru.itche.lettersproccesing.dto.user.CreateUserResponse;
import ru.itche.lettersproccesing.entity.auth.Role;
import ru.itche.lettersproccesing.entity.auth.User;
import ru.itche.lettersproccesing.repository.auth.RoleRepository;
import ru.itche.lettersproccesing.repository.auth.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserResponse createUser(CreateElfRequest request) {

        if (userRepository.existsByLogin(request.login())) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }

        Role role = roleRepository.findByName(request.role())
                .orElseThrow(() ->
                        new IllegalArgumentException("Роли нет: " + request.role())
                );

        User user = new User();
        user.setLogin(request.login());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(role);

        User saved = userRepository.save(user);

        return new CreateUserResponse(
                saved.getId(),
                saved.getLogin(),
                saved.getRole().getName()
        );
    }
}
