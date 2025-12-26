package ru.itche.lettersproccesing.service.letter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.lettersproccesing.dto.letter.CreateLetterRequest;
import ru.itche.lettersproccesing.dto.letter.CreateLetterResponse;
import ru.itche.lettersproccesing.entity.Gift;
import ru.itche.lettersproccesing.entity.Letter;
import ru.itche.lettersproccesing.repository.letter.LetterRepository;
import ru.itche.lettersproccesing.entity.valueobject.FullName;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    @Transactional
    public CreateLetterResponse create(CreateLetterRequest request) {

        Letter letter = new Letter();

        letter.setFullName(new FullName(
                request.lastName(),
                request.firstName(),
                request.patronymic()
        ));

        letter.setAge(request.age());
        letter.setCity(request.city());
        letter.setTextLetter(request.textLetter());

        if (request.gifts() != null) {
            request.gifts().forEach(g -> {
                Gift gift = new Gift();
                gift.setNameGift(g.nameGift());
                gift.setPrice(g.price());
                letter.addGift(gift);
            });
        }

        Letter saved = letterRepository.save(letter);

        return mapToResponse(saved);
    }

    private CreateLetterResponse mapToResponse(Letter letter) {
        return new CreateLetterResponse(
                letter.getId(),
                letter.getFullName().getFirstName(),
                letter.getCity(),
                letter.getGifts().size()
        );
    }
}

