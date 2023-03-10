package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.JwtService;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.PlayerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import static cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.constants.Constants.AUTHORIZATION;
import static cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.constants.Constants.BEARER;

@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final PlayerService playerService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(BEARER.length());

        String userIdentifier = jwtService.extractUsername(jwt);

        if (userIdentifier != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Player player  = this.playerService.getPlayerById(userIdentifier);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    player.getId(),
                    null,
                    player.getAuthorities());

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        filterChain.doFilter(request, response);
    }

}
