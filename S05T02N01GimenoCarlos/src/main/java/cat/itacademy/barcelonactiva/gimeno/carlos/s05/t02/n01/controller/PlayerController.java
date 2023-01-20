package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.GamesDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.PlayerDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.exceptions.UnauthorizedException;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.GamesService;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.services.interfaces.PlayerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/player")
@AllArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final GamesService gamesService;

    @PutMapping(value = "")
    public ResponseEntity<?> updatePlayer(@RequestBody(required = true) PlayerDto playerDto, Principal principal) {
        this.validateOwnerAction(playerDto.id, principal.getName());
        PlayerDto playerDto2 = this.playerService.updatePlayer(playerDto);
        return ResponseEntity.status(HttpStatus.OK).body(playerDto2);
    }

    @PostMapping(value = "/{id}/games")
    public ResponseEntity<GamesDto> playDados(@PathVariable(name = "id") String idPlayer, Principal principal) {
        this.validateOwnerAction(idPlayer, principal.getName());
        GamesDto game = this.gamesService.launchDices(idPlayer);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @DeleteMapping(value = "/{id}/games")
    public ResponseEntity<Void> deletePlayerData(@PathVariable(name = "id") String idPlayer, Principal principal) {
        this.validateOwnerAction(idPlayer, principal.getName());
        this.gamesService.deleteGamesFromPlayer(idPlayer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "")
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        List<PlayerDto> list = this.playerService.getAllPlayers();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/{id}/games")
    public ResponseEntity<List<GamesDto>> getOnePlayerData(@PathVariable(name = "id") String idPlayer) {
        List<GamesDto> list = this.gamesService.getPlayerGames(idPlayer);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/ranking")
    public ResponseEntity<List<PlayerDto>> getPlayersRanking() {
        List<PlayerDto> list = this.playerService.getPlayersRanking();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/ranking/winner")
    public ResponseEntity<PlayerDto> getRankingWinner() {
        PlayerDto playerDto = this.playerService.getBestPlayer();
        return ResponseEntity.status(HttpStatus.OK).body(playerDto);
    }

    @GetMapping(value = "/ranking/loser")
    public ResponseEntity<PlayerDto> getRankingLoser() {
        PlayerDto playerDto = this.playerService.getWorstPlayer();
        return ResponseEntity.status(HttpStatus.OK).body(playerDto);
    }

    private void validateOwnerAction(String idAction, String idAuth) {
        if (!idAction.equals(idAuth)) {
            throw new UnauthorizedException("Usted no tiene permisos para dicha accion.");
        }
    }
}
