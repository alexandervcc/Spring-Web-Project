package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.GamesDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.NotFoundException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.ServerErrorException;
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
    @Transactional(rollbackFor = ServerErrorException.class)
    public void deleteGamesFromPlayer(String idPlayer) {
        Optional<Player> p = this.playerRepository.findById(idPlayer);
        if (p.isEmpty()) {
            throw new NotFoundException("Jugador no encontrado para id: " + idPlayer);
        }
        // Actualizar coleccion de juegos
        List<Games> listGames = p.get().getListGames();
        try {
            this.gamesRepository.deleteAll(listGames);
            // Actualizar coleccion de player
            Player player = p.get();
            player.setListGames(new ArrayList<Games>());
            this.playerRepository.save(player);
        } catch (Exception e) {
            throw new ServerErrorException("Error en persistencia de db.");
        }
    }

    @Override
    public List<GamesDto> getPlayerGames(String idPlayer) {
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
    @Transactional(rollbackFor = ServerErrorException.class)
    public GamesDto launchDices(String idPlayer) {
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

        try {
            newGame = this.gamesRepository.save(newGame);
            List<Games> playerGames = currentPlayer.getListGames();
            playerGames.add(newGame);
            currentPlayer.setListGames(playerGames);
            this.playerRepository.save(currentPlayer);
        } catch (Exception e) {
            throw new ServerErrorException("Error en persistencia de db.");
        }

        return this.gamesMapper.convertToDto(newGame);
    }

    @Override
    public double calculatePercentage(String idPlayer) {
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
