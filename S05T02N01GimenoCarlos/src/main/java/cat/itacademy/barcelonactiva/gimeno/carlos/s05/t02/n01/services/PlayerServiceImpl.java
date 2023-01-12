package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.PlayerDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.PlayerService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public PlayerDto createNewPlayer(PlayerDto playerDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayerDto getBestPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PlayerDto> getPlayersRanking() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayerDto getWorstPlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
