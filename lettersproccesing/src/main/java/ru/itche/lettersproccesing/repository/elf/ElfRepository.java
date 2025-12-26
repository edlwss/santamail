package ru.itche.lettersproccesing.repository.elf;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itche.lettersproccesing.entity.Elf;

public interface ElfRepository extends JpaRepository<Elf, Long> {
}
