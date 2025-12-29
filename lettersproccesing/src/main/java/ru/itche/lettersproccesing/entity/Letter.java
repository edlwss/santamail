package ru.itche.lettersproccesing.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.itche.lettersproccesing.entity.valueobject.FullName;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "letters")
public class Letter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letter_generator")
    @SequenceGenerator(name = "letter_generator", sequenceName = "let_id_seq", allocationSize = 1)
    private Long id;

    @Embedded
    private FullName fullName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "text_letter", nullable = false)
    private String textLetter;

    @OneToMany(mappedBy = "letter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gift> gifts = new ArrayList<>();

    public void addGift(Gift gift) {
        gifts.add(gift);
        gift.setLetter(this);
    }

    @OneToOne(mappedBy = "letter", cascade = CascadeType.ALL, orphanRemoval = true)
    private LetterStatus status;

    public void setStatus(LetterStatus status) {
        this.status = status;
        status.setLetter(this);
    }

}
