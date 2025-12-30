package ru.itche.lettersproccesing.controller.letter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.lettersproccesing.dto.letter.AddElfForLetterRequest;
import ru.itche.lettersproccesing.dto.letter.AddElfForLetterResponse;
import ru.itche.lettersproccesing.dto.letter.CreateLetterRequest;
import ru.itche.lettersproccesing.dto.letter.CreateLetterResponse;
import ru.itche.lettersproccesing.dto.letter.GetLetter;
import ru.itche.lettersproccesing.dto.letter.UpdateLetterRequest;
import ru.itche.lettersproccesing.dto.letter.UpdateLetterResponse;
import ru.itche.lettersproccesing.entity.EnumLetterStatus;
import ru.itche.lettersproccesing.service.letter.LetterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LetterController {

    private final LetterService letterService;

    @GetMapping("/admin/letters/{status}")
    public ResponseEntity<List<GetLetter>> getLettersByStatus(
            @PathVariable
            @Schema(
                    description = "Статус письма",
                    example = "WAITING_APPROVAL",
                    implementation = EnumLetterStatus.class
            )
            EnumLetterStatus status
    ) {
        return ResponseEntity.ok(letterService.getLettersByStatus(status));
    }


    @PostMapping("/letter/create")
    public ResponseEntity<CreateLetterResponse> createLetter(@RequestBody CreateLetterRequest request) {
        CreateLetterResponse response = letterService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/admin/letter/{id}/update/status")
    public ResponseEntity<UpdateLetterResponse> updateLetterStatus(@PathVariable Long id,
                                                                   @RequestBody UpdateLetterRequest request) {
        UpdateLetterResponse response = letterService.updateStatusLetter(id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/admin/letter/{id}/add/elf")
    public ResponseEntity<AddElfForLetterResponse> updateLetterStatus(@PathVariable Long id,
                                                                      @RequestBody AddElfForLetterRequest request) {
        AddElfForLetterResponse response = letterService.addElfForLetter(id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
