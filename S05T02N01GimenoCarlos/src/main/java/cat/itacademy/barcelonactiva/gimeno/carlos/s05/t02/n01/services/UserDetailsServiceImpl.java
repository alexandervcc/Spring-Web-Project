package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.PlayerRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        List<Player> playerList = this.playerRepository.findByNombre(nombre);
        if (playerList.size() == 0) {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
        return playerList.get(0);
    }

    public Player findById(String id) throws UsernameNotFoundException {
        Optional<Player> playerOptional = this.playerRepository.findById(id);
        if (playerOptional.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
        return playerOptional.get();
    }
}
