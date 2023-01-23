package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.JwtService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTServiceImpl implements JwtService {
    private final Algorithm algorithm;

    @Override
    public String extractUsername(String jwt) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(jwt);
        return decodedJWT.getSubject();
    }

    @Override
    public String generateToken(Player player) {
        List<String> listAuthorities = player
                .getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .toList();

        return JWT.create()
                .withSubject(player.getId())
                .withClaim("roles", listAuthorities)
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(algorithm);
    }

}
