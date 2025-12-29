package ru.itche.lettersproccesing.entity.auth;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_role", nullable = false, unique = true)
    private RoleName name;
}
