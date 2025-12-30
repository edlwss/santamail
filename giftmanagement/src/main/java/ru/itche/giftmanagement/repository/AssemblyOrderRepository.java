package ru.itche.giftmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itche.giftmanagement.entity.AssemblyOrder;

@Repository
public interface AssemblyOrderRepository extends JpaRepository<AssemblyOrder, Long> {
}
