package ru.itche.lettersproccesing.repository.gift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itche.lettersproccesing.entity.Gift;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {
}
