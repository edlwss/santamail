package ru.itche.giftmanagement.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.itche.giftmanagement.dto.letter.GetLetter;

@Service
@RequiredArgsConstructor
public class LetterClient {

    private final WebClient letterWebClient;

    public GetLetter getLetterById(Long letterId) {
        return letterWebClient.get()
                .uri("/letter/{id}", letterId)
                .retrieve()
                .bodyToMono(GetLetter.class)
                .block();
    }
}

