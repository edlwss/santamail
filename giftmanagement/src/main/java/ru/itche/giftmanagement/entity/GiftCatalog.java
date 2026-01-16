package ru.itche.giftmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gift_catalog")
public class GiftCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalog_generator")
    @SequenceGenerator(name = "catalog_generator", sequenceName = "catalog_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name_gift", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock_total", nullable = false)
    private Integer stockTotal;

    @Column(name = "stock_reserved", nullable = false)
    private Integer stockReserved;

    public Integer getAvailable() {
        return stockTotal - stockReserved;
    }
}

