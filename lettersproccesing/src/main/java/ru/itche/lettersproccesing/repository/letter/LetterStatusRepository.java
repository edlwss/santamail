package ru.itche.lettersproccesing.repository.letter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itche.lettersproccesing.entity.EnumLetterStatus;
import ru.itche.lettersproccesing.entity.LetterStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface LetterStatusRepository extends JpaRepository<LetterStatus, Long> {

    Optional<LetterStatus> findByLetterId(Long letterId);

    List<LetterStatus> findAllByStatus(EnumLetterStatus status);
}
