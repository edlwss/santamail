package ru.itche.lettersproccesing.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itche.lettersproccesing.entity.auth.Role;
import ru.itche.lettersproccesing.entity.auth.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}

