package ru.itche.lettersproccesing.repository.auth;

import ru.itche.lettersproccesing.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
