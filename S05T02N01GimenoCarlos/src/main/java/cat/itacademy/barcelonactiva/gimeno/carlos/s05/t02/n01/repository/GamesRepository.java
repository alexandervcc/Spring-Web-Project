package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;

public interface GamesRepository extends MongoRepository<Games, String> {

}
