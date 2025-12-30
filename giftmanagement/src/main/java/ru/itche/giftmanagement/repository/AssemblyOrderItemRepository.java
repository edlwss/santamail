package ru.itche.giftmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itche.giftmanagement.entity.AssemblyOrderItem;

@Repository
public interface AssemblyOrderItemRepository extends JpaRepository<AssemblyOrderItem, Long> {
}
