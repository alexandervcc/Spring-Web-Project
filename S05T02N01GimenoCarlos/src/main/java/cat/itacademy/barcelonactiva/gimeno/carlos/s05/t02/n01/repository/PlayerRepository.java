package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {
   List<Player> findByNombre(String nombre);

   Optional<Player> findByNombreAndPassword(String nombre, String password);
}
