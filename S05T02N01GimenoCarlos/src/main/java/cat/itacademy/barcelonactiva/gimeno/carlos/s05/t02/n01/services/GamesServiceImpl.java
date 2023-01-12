package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.GamesDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.GamesRepository;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.GamesService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GamesServiceImpl implements GamesService {
    private final GamesRepository gamesRepository;

    @Override
    public void deleteGamesFromPlayer(Integer idPlayer) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<GamesDto> getPlayerGames(Integer idPlayer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GamesDto launchDices(Integer idPlayer) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
