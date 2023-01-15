package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
   List<Player> findByNombre(String nombre);

   Player findByEmail(String email);
}
