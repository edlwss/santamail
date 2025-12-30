package ru.itche.lettersproccesing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "gifts")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gift_generator")
    @SequenceGenerator(name = "gift_generator", sequenceName = "gift_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name_gift", nullable = false)
    private String nameGift;

    @Column(name = "price", nullable = false)
    private double price;

    @JoinColumn(name = "letter_id", nullable = false)
    @ManyToOne
    private Letter letter;
}
