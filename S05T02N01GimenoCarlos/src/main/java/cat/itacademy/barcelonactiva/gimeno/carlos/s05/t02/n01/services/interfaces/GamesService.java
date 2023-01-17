package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces;

import java.util.List;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.GamesDto;

public interface GamesService {
    public GamesDto launchDices(String idPlayer);
    public void deleteGamesFromPlayer(String idPlayer);
    public List<GamesDto> getPlayerGames(String idPlayer);
    public double calculatePercentage(String idPlayer);
}
