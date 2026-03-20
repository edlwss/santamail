package ru.itche.lettersproccesing.repository.letter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itche.lettersproccesing.entity.Letter;

import java.util.Optional;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {

    @Query("SELECT l FROM Letter l LEFT JOIN FETCH l.gifts WHERE l.id = :id")
    Optional<Letter> findByIdWithGifts(@Param("id") Long id);

}
