package ru.itche.lettersproccesing.controller.letter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.lettersproccesing.dto.letter.CreateLetterRequest;
import ru.itche.lettersproccesing.dto.letter.CreateLetterResponse;
import ru.itche.lettersproccesing.service.letter.LetterService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/letters")
public class LetterController {

    private final LetterService letterService;

    @PostMapping("/create")
    public ResponseEntity<CreateLetterResponse> createLetter(@RequestBody CreateLetterRequest request) {
        CreateLetterResponse response = letterService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
