package rs.ac.ftn.uns.upp.scientificcenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rs.ac.ftn.uns.upp.scientificcenter.dto.LoginRequest;
import rs.ac.ftn.uns.upp.scientificcenter.dto.PrincipleDto;

import java.util.Map;

@RequestMapping("/user/api/auth")
@RestController
@CrossOrigin(value = {"http://localhost:3000"}, allowCredentials = "true")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public PrincipleDto getPrincipal(Authentication authentication) {
        PrincipleDto principleDto = new PrincipleDto();

        if (authentication == null)
            return principleDto;

        principleDto.setUsername(authentication.getName());
        return principleDto;
    }

    @PostMapping
    public Authentication auth(LoginRequest loginRequest) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
    }

}