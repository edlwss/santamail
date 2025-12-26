package ru.itche.lettersproccesing.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ru.itche.lettersproccesing.entity.auth.Role;
import ru.itche.lettersproccesing.entity.auth.RoleName;
import ru.itche.lettersproccesing.entity.auth.User;
import ru.itche.lettersproccesing.repository.auth.RoleRepository;
import ru.itche.lettersproccesing.repository.auth.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(String login, String rawPassword, RoleName roleName) {

        if (userRepository.existsByLogin(login)) {
            throw new IllegalArgumentException("Логин уже существует");
        }

        Role role = roleRepository.findByName(roleName.name())
                .orElseThrow(() -> new IllegalStateException("Роль не найдена"));

        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);

        return userRepository.save(user);
    }
}

