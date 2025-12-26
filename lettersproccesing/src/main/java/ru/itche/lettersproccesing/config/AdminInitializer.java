package ru.itche.lettersproccesing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itche.lettersproccesing.entity.auth.Role;
import ru.itche.lettersproccesing.entity.auth.User;
import ru.itche.lettersproccesing.repository.auth.RoleRepository;
import ru.itche.lettersproccesing.repository.auth.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.login}")
    private String adminLogin;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Override
    public void run(ApplicationArguments args) {

        if (userRepository.existsByLogin(adminLogin)) {
            return;
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() ->
                        new IllegalStateException("ROLE_ADMIN не существует")
                );

        User admin = new User();
        admin.setLogin(adminLogin);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRole(adminRole);

        userRepository.save(admin);

        System.out.println("Аккаунт администратора по умолчанию создан");
    }
}

