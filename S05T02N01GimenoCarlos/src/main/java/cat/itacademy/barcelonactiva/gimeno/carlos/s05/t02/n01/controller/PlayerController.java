package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/player")
@AllArgsConstructor
public class PlayerController {

    @PostMapping(value = "")
    public ResponseEntity<?> createNewPlayer() {

        return ResponseEntity.status(HttpStatus.CREATED).body("Jugador creado");
    }

    @PutMapping(value = "")
    public ResponseEntity<?> updatePlayer() {

        return ResponseEntity.status(HttpStatus.OK).body("Jugador actualizado");
    }

    @PostMapping(value = "/{id}/games")
    public ResponseEntity<?> playDados(@PathVariable(name = "id") Integer idPlayer) {

        return ResponseEntity.status(HttpStatus.OK).body("Dados lanzados");
    }

    @DeleteMapping(value = "/{id}/games")
    public ResponseEntity<?> deletePlayerData(@PathVariable(name = "id") Integer idPlayer) {

        return ResponseEntity.status(HttpStatus.OK).body("Tiros eliminados");
    }

    @GetMapping(value = "")
    public ResponseEntity<List<?>> getPlayers() {

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @GetMapping(value = "/{id}/games")
    public ResponseEntity<?> getOnePlayerData(@PathVariable(name = "id") Integer idPlayer) {

        return ResponseEntity.status(HttpStatus.OK).body("Datos del jugador especifico");
    }

    @GetMapping(value = "/ranking")
    public ResponseEntity<?> getPlayersRanking() {

        return ResponseEntity.status(HttpStatus.OK).body("Players Ranking");
    }

    @GetMapping(value = "/ranking/winner")
    public ResponseEntity<?> getRankingWinner() {

        return ResponseEntity.status(HttpStatus.OK).body("Winner");
    }

    @GetMapping(value = "/ranking/lose")
    public ResponseEntity<?> getRankingLoser() {

        return ResponseEntity.status(HttpStatus.OK).body("Loser");
    } 

}
