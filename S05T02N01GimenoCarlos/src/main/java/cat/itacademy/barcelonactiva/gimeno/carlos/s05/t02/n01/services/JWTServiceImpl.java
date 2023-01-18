package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.JwtService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTServiceImpl implements JwtService {
    @Override
    public String generateToken(Player player) {
        // TODO Auto-generated method stub
        return null;
    }

}
