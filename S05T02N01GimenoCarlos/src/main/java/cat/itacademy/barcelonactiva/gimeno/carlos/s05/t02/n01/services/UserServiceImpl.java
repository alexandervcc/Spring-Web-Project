package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Player getUser(String email) {
        return this.playerRepository.findByEmail(email);
    }

    @Override
    public List<Player> getUsers() {
        return this.playerRepository.findAll();
    }

    @Override
    public Player saveUser(Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        return this.playerRepository.save(player);
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        List<Player> playerList = this.playerRepository.findByNombre(nombre);
        if (playerList.size() == 0) {
            throw new NotFoundException("Usuario no encontrado.");
        }
        return playerList.get(0);
    }
}
