package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces;

import java.util.List;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;

public interface UserService {
    Player saveUser(Player player);
    Player getUser(String email);
    List<Player> getUsers();
}
