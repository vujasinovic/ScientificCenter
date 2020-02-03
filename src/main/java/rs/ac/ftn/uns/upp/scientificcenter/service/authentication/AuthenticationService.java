package rs.ac.ftn.uns.upp.scientificcenter.service.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.config.security.CamundaUserDetailsService;
import rs.ac.ftn.uns.upp.scientificcenter.config.security.JwtTokenProvider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final CamundaUserDetailsService userDetailsService;

    public String authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Collection<? extends GrantedAuthority> authorities = userDetailsService.loadUserByUsername(username).getAuthorities();
        List<String> authorityNames = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return jwtTokenProvider.createToken(username, authorityNames);
    }

}
