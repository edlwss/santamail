package ru.itche.lettersproccesing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "letters_status")
public class LetterStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_generator")
    @SequenceGenerator(name = "status_generator", sequenceName = "status_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_letter", nullable = false)
    private EnumLetterStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_id", nullable = false, unique = true)
    private Letter letter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "elf_id")
    private Elf elf;
}

