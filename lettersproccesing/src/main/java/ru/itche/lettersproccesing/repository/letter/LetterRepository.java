package ru.itche.lettersproccesing.repository.letter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itche.lettersproccesing.entity.Letter;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
}
