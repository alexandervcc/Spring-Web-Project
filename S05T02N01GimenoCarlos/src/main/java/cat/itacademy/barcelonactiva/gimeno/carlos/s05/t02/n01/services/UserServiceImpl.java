package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.InvalidCredentialsException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.PlayerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        List<Player> playerList = this.playerRepository.findByNombre(nombre);
        if (playerList.size() == 0) {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
        return playerList.get(0);
    }

    public Player authenticateUser(String nombre, String contrasena) {
        Player player = (Player) this.loadUserByUsername(nombre);
        if (!passwordEncoder.matches(contrasena, player.getPassword())) {
            throw new InvalidCredentialsException("Contrasena incorrecta.");
        }
        return player;
    }
}
