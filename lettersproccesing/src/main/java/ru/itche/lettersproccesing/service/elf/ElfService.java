package ru.itche.lettersproccesing.service.elf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.lettersproccesing.dto.elf.CreateElfRequest;
import ru.itche.lettersproccesing.dto.elf.CreateElfResponse;
import ru.itche.lettersproccesing.entity.Elf;
import ru.itche.lettersproccesing.entity.auth.RoleName;
import ru.itche.lettersproccesing.entity.auth.User;
import ru.itche.lettersproccesing.repository.elf.ElfRepository;
import ru.itche.lettersproccesing.service.user.UserService;

@Service
@AllArgsConstructor
public class ElfService {

    private final ElfRepository elfRepository;
    private final UserService userService;

    @Transactional
    public CreateElfResponse createElf(CreateElfRequest request) {

        User user = userService.createUser(
                request.login(),
                request.password(),
                RoleName.ROLE_ELF
        );

        Elf elf = new Elf();
        elf.setUser(user);
        elf.setNameElf(request.nameElf());
        elfRepository.save(elf);

        return new CreateElfResponse( //сделать внутри пейлода метод from?
                elf.getUser().getId(),
                elf.getUser().getLogin(),
                elf.getNameElf(),
                elf.getUser().getRole().getName()
        );
    }

}
