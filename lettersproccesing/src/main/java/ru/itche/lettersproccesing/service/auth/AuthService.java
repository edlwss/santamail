package ru.itche.lettersproccesing.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itche.lettersproccesing.dto.auth.AuthRequest;
import ru.itche.lettersproccesing.dto.auth.AuthResponse;
import ru.itche.lettersproccesing.security.JwtService;
import ru.itche.lettersproccesing.security.UserDetailsServiceImpl;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsServiceImpl userService;
    private final JwtService jwtToken;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.login(),
                        authRequest.password()
                )
        );

        UserDetails userDetails = userService.loadUserByUsername(authRequest.login());
        String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
