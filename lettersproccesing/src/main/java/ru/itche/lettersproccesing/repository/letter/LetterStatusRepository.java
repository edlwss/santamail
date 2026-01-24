package ru.itche.lettersproccesing.repository.letter;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itche.lettersproccesing.entity.EnumLetterStatus;
import ru.itche.lettersproccesing.entity.LetterStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface LetterStatusRepository extends JpaRepository<LetterStatus, Long> {

    Optional<LetterStatus> findByLetterId(Long letterId);

    @Query(value = """
    select ls.*
    from lettersproc.letters_status ls
    join lettersproc.letters l on l.id = ls.letter_id
    where (:status is null or ls.status_letter = :status)
      and (:city is null or l.city ilike concat('%', :city, '%'))
    order by ls.letter_id desc
""", nativeQuery = true)
    List<LetterStatus> findAllByStatusAndCity(
            @Param("status") String status,
            @Param("city") String city
    );
}
