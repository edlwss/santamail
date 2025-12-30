package ru.itche.giftmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "assembly_order_item")
public class AssemblyOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
    @SequenceGenerator(name = "item_generator", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private AssemblyOrder order;

    @ManyToOne()
    @JoinColumn(name = "gift_id", nullable = false)
    private GiftCatalog gift;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

}

