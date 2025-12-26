package ru.itche.lettersproccesing.controller.elf;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.lettersproccesing.dto.elf.CreateElfRequest;
import ru.itche.lettersproccesing.dto.elf.CreateElfResponse;
import ru.itche.lettersproccesing.service.elf.ElfService;

@RestController
@RequestMapping("/admin/elf")
@RequiredArgsConstructor
public class ElfController {

    private final ElfService elfService;

    @PostMapping("/registration")
    public ResponseEntity<CreateElfResponse> createElf(@RequestBody CreateElfRequest request) {
        CreateElfResponse response = elfService.createElf(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
