package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.ReqAuthDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Role;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.InvalidDataException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.AuthServive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthServive {
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerRepository;
    private final JWTServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String logIn(ReqAuthDto authDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.name, authDto.password));

        } catch (Exception e) {
            log.info("\n\n" + e.getMessage());
        }
        List<Player> player = this.playerRepository.findByNombre(authDto.name);
        String token = this.jwtService.generateToken(player.get(0));
        log.info("\n\n4\n\n");
        return token;
    }

    @Override
    public void signUp(ReqAuthDto playerDto) {
        Role role = Role.ANONYMOUS;
        if (!playerDto.name.toUpperCase().equals("ANONIMO")) {
            List<Player> l = this.playerRepository.findByNombre(playerDto.name);
            if (l.size() > 0) {
                throw new InvalidDataException("El nombre: " + playerDto.name + " esta en uso.");
            }
            role = Role.USER;
        }

        Player newPlayer = Player.builder()
                .nombre(playerDto.name)
                .fechaRegistro(new Date())
                .password(passwordEncoder.encode(playerDto.password))
                .role(role)
                .listGames(new ArrayList<Games>())
                .build();

        newPlayer = this.playerRepository.save(newPlayer);
    }

}
