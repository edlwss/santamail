package ru.itche.giftmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itche.giftmanagement.entity.GiftCatalog;

import java.util.List;

@Repository
public interface GiftCatalogRepository extends JpaRepository<GiftCatalog, Long> {

    @Query("""
    SELECT g
    FROM GiftCatalog g
    WHERE LOWER(g.name) LIKE CONCAT('%', LOWER(:names), '%')
""")
    List<GiftCatalog> findAllByNameIgnoreCase(@Param("names") List<String> names);
}


