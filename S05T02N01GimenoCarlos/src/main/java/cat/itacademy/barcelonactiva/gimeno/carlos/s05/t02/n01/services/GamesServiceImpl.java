package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.GamesDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.GamesRepository;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.GamesService;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.utils.GamesMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GamesServiceImpl implements GamesService {
    private final GamesRepository gamesRepository;
    private final PlayerRepository playerRepository;
    private final GamesMapper gamesMapper;

    @Override
    public void deleteGamesFromPlayer(Integer idPlayer) {
        Optional<Player> p = this.playerRepository.findById(idPlayer);
        if (p.isEmpty()) {
            throw new NotFoundException("Jugador no encontrado para id: " + idPlayer);
        }
        List<Games> listGames = p.get().getListGames();
        this.gamesRepository.deleteAllInBatch(listGames);
    }

    @Override
    public List<GamesDto> getPlayerGames(Integer idPlayer) {
        Optional<Player> p = this.playerRepository.findById(idPlayer);
        if (p.isEmpty()) {
            throw new NotFoundException("Jugador no encontrado para id: " + idPlayer);
        }
        List<GamesDto> list = p.get().getListGames()
                .stream()
                .map(g -> this.gamesMapper.convertToDto(g))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public GamesDto launchDices(Integer idPlayer) {
        Optional<Player> playerOptional = this.playerRepository.findById(idPlayer);
        if (playerOptional.isEmpty()) {
            throw new NotFoundException("Jugador no encontrado para id: " + idPlayer);
        }
        // Math.random() -> 0.0 - 1.0
        // *10 -> 0.0 -> 10.0
        // %6 -> 0.0 -> 5.0
        // +1 -> 1.2 -> 6.002
        // (int) -> 1 -> 6
        int d1 = (int) (Math.random() * 10) % 6 + 1;
        int d2 = (int) (Math.random() * 10) % 6 + 1;

        int sum = d1 + d2;
        boolean win = sum == 7;
        Player currentPlayer = playerOptional.get();// denvoltura

        Games newGame = Games.builder()
                .result(win)
                .value(sum)
                .player(currentPlayer)
                .build();

        newGame = this.gamesRepository.save(newGame);
        return this.gamesMapper.convertToDto(newGame);
    }

    @Override
    public double calculatePercentage(Integer idPlayer) {
        List<GamesDto> games = this.getPlayerGames(idPlayer);
        double totalGames = games.size();
        double winGames = 0;
        for (GamesDto g : games) {
            if (g.result) {
                winGames++;
            }
        }
        return (winGames / totalGames) * 100;
    }

}
