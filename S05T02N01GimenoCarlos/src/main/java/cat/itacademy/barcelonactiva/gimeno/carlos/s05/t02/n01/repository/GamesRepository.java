package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;

@Repository
public interface GamesRepository extends MongoRepository<Games, String> {

}
