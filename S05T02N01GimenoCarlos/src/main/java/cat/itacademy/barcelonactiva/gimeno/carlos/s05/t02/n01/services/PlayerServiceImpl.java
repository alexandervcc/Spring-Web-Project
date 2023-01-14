package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.PlayerDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.InvalidDataException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.GamesService;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.PlayerService;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.utils.PlayerMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final GamesService gamesService;

    @Override
    public PlayerDto createNewPlayer(PlayerDto playerDto) {
        if (!playerDto.nombre.toUpperCase().equals("ANONIMO")) {
            List<Player> l = this.playerRepository.findByNombre(playerDto.nombre);
            if (l.size() > 0) {
                throw new InvalidDataException("El nombre: " + playerDto.nombre + " esta en uso.");
            }
        }

        Player newPlayer = Player.builder()
                .nombre(playerDto.nombre)
                .fechaRegistro(new Date())
                .listGames(new ArrayList<Games>())
                .build();

        newPlayer = this.playerRepository.save(newPlayer);
        return this.playerMapper.convertToDto(newPlayer);
    }

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<PlayerDto> players = this.playerRepository.findAll()
                .stream()
                .map(j -> this.playerMapper.convertToDto(j))
                .collect(Collectors.toList());

        players.forEach(j -> {
            j.percentage = this.gamesService.calculatePercentage(j.id);
        });
        return players;
    }

    @Override
    public PlayerDto getBestPlayer() {
        List<PlayerDto> list = this.getAllPlayers();
        list.sort((j1, j2) -> {
            if (j1.percentage > j2.percentage) {
                return 1;
            }
            if (j1.percentage < j2.percentage) {
                return -1;
            }
            return 0;
        });
        return list.get(0);
    }

    @Override
    public List<PlayerDto> getPlayersRanking() {
        List<PlayerDto> list = this.getAllPlayers();
        list.sort((j1, j2) -> {
            if (j1.percentage > j2.percentage) {
                return 1;
            }
            if (j1.percentage < j2.percentage) {
                return -1;
            }
            return 0;
        });
        return list;
    }

    @Override
    public PlayerDto getWorstPlayer() {
        List<PlayerDto> list = this.getAllPlayers();
        list.sort((j1, j2) -> {
            if (j1.percentage > j2.percentage) {
                return 1;
            }
            if (j1.percentage < j2.percentage) {
                return -1;
            }
            return 0;
        });
        return list.get(list.size() - 1);
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto) {
        if (!playerDto.nombre.toUpperCase().equals("ANONIMO")) {
            List<Player> l = this.playerRepository.findByNombre(playerDto.nombre);
            if (l.size() > 0) {
                throw new InvalidDataException("El nombre: " + playerDto.nombre + " esta en uso.");
            }
        }
        Optional<Player> playerOp = this.playerRepository.findById(playerDto.id);
        if (playerOp.isEmpty()) {
            throw new NotFoundException("Jugador no encontrado para id: " + playerDto.id);
        }
        Player p = playerOp.get();
        p.setNombre(playerDto.nombre);
        p = this.playerRepository.save(p);
        return this.playerMapper.convertToDto(p);
    }

}
