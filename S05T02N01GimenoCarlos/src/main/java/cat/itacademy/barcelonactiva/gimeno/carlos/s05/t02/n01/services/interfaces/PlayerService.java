package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces;

import java.util.List;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.PlayerDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;

public interface PlayerService {
    public PlayerDto updatePlayer(PlayerDto playerDto);

    public List<PlayerDto> getAllPlayers();

    public List<PlayerDto> getPlayersRanking();

    public PlayerDto getBestPlayer();

    public PlayerDto getWorstPlayer();

    public Player getPlayerById(String id);
}
