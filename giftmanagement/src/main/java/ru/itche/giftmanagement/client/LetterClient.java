package ru.itche.giftmanagement.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.itche.giftmanagement.dto.letter.GetLetter;

@Component
@RequiredArgsConstructor
public class LetterClient {

    private final RestClient letterRestClient;

    public GetLetter getLetterById(Long letterId) {
        return letterRestClient.get()
                .uri("/letter/{id}", letterId)
                .retrieve()
                .body(GetLetter.class);
    }
}


