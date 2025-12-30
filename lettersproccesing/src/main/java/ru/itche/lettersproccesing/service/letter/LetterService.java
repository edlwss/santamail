package ru.itche.lettersproccesing.service.letter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.lettersproccesing.dto.gift.GetGift;
import ru.itche.lettersproccesing.dto.kafka.GiftApprovalEvent;
import ru.itche.lettersproccesing.dto.letter.AddElfForLetterRequest;
import ru.itche.lettersproccesing.dto.letter.AddElfForLetterResponse;
import ru.itche.lettersproccesing.dto.letter.CreateLetterRequest;
import ru.itche.lettersproccesing.dto.letter.CreateLetterResponse;
import ru.itche.lettersproccesing.dto.letter.GetLetter;
import ru.itche.lettersproccesing.dto.letter.UpdateLetterRequest;
import ru.itche.lettersproccesing.dto.letter.UpdateLetterResponse;
import ru.itche.lettersproccesing.entity.Elf;
import ru.itche.lettersproccesing.entity.EnumLetterStatus;
import ru.itche.lettersproccesing.entity.Gift;
import ru.itche.lettersproccesing.entity.Letter;
import ru.itche.lettersproccesing.entity.LetterStatus;
import ru.itche.lettersproccesing.kafka.GiftApprovalProducer;
import ru.itche.lettersproccesing.repository.elf.ElfRepository;
import ru.itche.lettersproccesing.repository.letter.LetterRepository;
import ru.itche.lettersproccesing.entity.valueobject.FullName;
import ru.itche.lettersproccesing.repository.letter.LetterStatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;
    private final LetterStatusRepository letterStatusRepository;
    private final GiftApprovalProducer giftApprovalProducer;
    private final ElfRepository elfRepository;

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
        Letter savedLetter = letterRepository.save(letter);

        LetterStatus status = new LetterStatus();
        status.setLetter(savedLetter);
        status.setElf(null);
        status.setStatus(
                checkPriceGiftsLetter(savedLetter.getGifts())
                        ? EnumLetterStatus.WAITING_APPROVAL
                        : EnumLetterStatus.RECEIVED
        );

        letterStatusRepository.save(status);

        return mapToResponse(savedLetter, status);
    }

    private boolean checkPriceGiftsLetter(List<Gift> gifts) {
        boolean hasExpensiveGift = false;

        for (Gift gift : gifts) {
            if (gift.getPrice() > 5000) {
                hasExpensiveGift = true;

                GiftApprovalEvent event = new GiftApprovalEvent(
                        gift.getId(),
                        gift.getNameGift(),
                        gift.getPrice()
                );
                giftApprovalProducer.sendExpensiveGiftEvent(event);
            }
        }
        return hasExpensiveGift;
    }

    private CreateLetterResponse mapToResponse(Letter letter, LetterStatus status) {
        return new CreateLetterResponse(
                letter.getId(),
                letter.getFullName().getFirstName(),
                letter.getCity(),
                letter.getGifts().size(),
                status.getStatus()
        );
    }

    @Transactional
    public UpdateLetterResponse updateStatusLetter(Long id, UpdateLetterRequest request) {

        LetterStatus letterStatus = letterStatusRepository.findByLetterId(id)
                .orElseThrow(() -> new RuntimeException("Письмо с id " + id + " не найдено"));

        letterStatus.setStatus(request.statusLetter());
        letterStatusRepository.save(letterStatus);

        return new UpdateLetterResponse(
                letterStatus.getLetter().getId(),
                letterStatus.getStatus()
        );
    }

    @Transactional
    public AddElfForLetterResponse addElfForLetter(Long id, AddElfForLetterRequest request) {

        LetterStatus letterStatus = letterStatusRepository.findByLetterId(id)
                .orElseThrow(() -> new RuntimeException("Письмо с id " + id + " не найдено"));

        Elf elf = elfRepository.findById(request.idElf())
                .orElseThrow(() -> new RuntimeException("Эльф с id " + id + "не найден"));

        letterStatus.setElf(elf);
        letterStatusRepository.save(letterStatus);

        return new AddElfForLetterResponse(
                letterStatus.getLetter().getId(),
                letterStatus.getElf().getNameElf()
        );
    }

    @Transactional
    public List<GetLetter> getLettersByStatus(EnumLetterStatus status) {
        return letterStatusRepository.findAllByStatus(status).stream()
                .map(LetterStatus::getLetter)
                .map(this::mapToDto)
                .toList();
    }

    private GetLetter mapToDto(Letter letter) {
        return new GetLetter(
                letter.getId(),
                letter.getFullName().getLastName(),
                letter.getFullName().getFirstName(),
                letter.getFullName().getPatronymic(),
                letter.getAge(),
                letter.getCity(),
                letter.getTextLetter(),
                letter.getGifts().stream()
                        .map(gift -> new GetGift(
                                gift.getId(),
                                gift.getNameGift(),
                                gift.getPrice()
                        ))
                        .toList()
        );
    }

    public GetLetter getById(Long id) {

        Letter letter = letterRepository.findByIdWithGifts(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Такого письма не существует")
                );

        return new GetLetter(
                letter.getId(),
                letter.getFullName().getLastName(),
                letter.getFullName().getFirstName(),
                letter.getFullName().getPatronymic(),
                letter.getAge(),
                letter.getCity(),
                letter.getTextLetter(),
                letter.getGifts().stream()
                        .map(gift -> new GetGift(
                                gift.getId(),
                                gift.getNameGift(),
                                gift.getPrice()
                        ))
                        .toList()
        );
    }

}


