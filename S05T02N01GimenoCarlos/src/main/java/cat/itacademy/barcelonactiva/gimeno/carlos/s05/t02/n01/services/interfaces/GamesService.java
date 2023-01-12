package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces;

import java.util.List;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.GamesDto;

public interface GamesService {
    public GamesDto launchDices(Integer idPlayer);
    public void deleteGamesFromPlayer(Integer idPlayer);
    public List<GamesDto> getPlayerGames(Integer idPlayer);
    
}
