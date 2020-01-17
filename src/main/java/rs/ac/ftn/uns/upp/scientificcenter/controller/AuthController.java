package rs.ac.ftn.uns.upp.scientificcenter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import rs.ac.ftn.uns.upp.scientificcenter.dto.LoginRequest;
import rs.ac.ftn.uns.upp.scientificcenter.dto.TokenResponse;
import rs.ac.ftn.uns.upp.scientificcenter.service.AuthenticationService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/user/api/auth")
@RestController
@CrossOrigin(value = {"http://localhost:3000"}, allowCredentials = "true")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping
    @PreAuthorize("isFullyAuthenticated()")
    public Map<String, Object> getPrincipal(Authentication authentication) {
        if(authentication == null)
            return Map.of();

        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Map.of("username", authentication.getName(), "authorities", authorities);
    }

    @PostMapping()
    public ResponseEntity<TokenResponse> auth(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return ResponseEntity.ok(tokenResponse);
    }

}