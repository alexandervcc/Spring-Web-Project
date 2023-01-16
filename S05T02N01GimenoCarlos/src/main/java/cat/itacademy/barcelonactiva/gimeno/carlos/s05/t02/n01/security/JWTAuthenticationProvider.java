package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.UserServiceImpl;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {
    private final UserServiceImpl userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Player player = this.userService.authenticateUser(name, password);
        return new UsernamePasswordAuthenticationToken(
                name, player.getPassword(), new ArrayList<>());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
