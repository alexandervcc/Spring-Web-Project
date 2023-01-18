package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;

public interface JwtService {
    String generateToken(Player player);
}
