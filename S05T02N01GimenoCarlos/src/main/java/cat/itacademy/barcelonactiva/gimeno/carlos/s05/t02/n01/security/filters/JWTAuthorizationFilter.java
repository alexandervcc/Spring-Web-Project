package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import static cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.constants.Constants.AUTHORIZATION;
import static cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.constants.Constants.BEARER;

@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final Algorithm algorithm;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {
            String authHeader = request.getHeader(AUTHORIZATION);
            if (authHeader != null && authHeader.startsWith(BEARER)) {
                try {
                    String token = authHeader.substring(BEARER.length());
                    JWTVerifier verifier = JWT.require(algorithm).build();

                    DecodedJWT decodedJWT = verifier.verify(token);

                    String username = decodedJWT.getSubject();

                    String role = decodedJWT.getClaim("roles").asString();

                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

                    authorities.add(new SimpleGrantedAuthority(role));
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
                            null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(request, response);

                } catch (Exception e) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());

                    Map<String, String> errors = new HashMap<>();
                    errors.put("error", e.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), errors);
                }

            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

}
