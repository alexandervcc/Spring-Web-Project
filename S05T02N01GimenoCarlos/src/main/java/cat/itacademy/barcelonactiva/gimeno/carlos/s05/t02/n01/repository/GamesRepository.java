package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;

@Repository
public interface GamesRepository extends JpaRepository<Games,Integer> {
    
}
