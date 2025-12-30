package ru.itche.lettersproccesing.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import ru.itche.lettersproccesing.dto.auth.AuthRequest;
import org.springframework.web.bind.annotation.*;
import ru.itche.lettersproccesing.service.auth.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) { //добавить валидацию
        return authService.createAuthToken(authRequest);
    }
}

