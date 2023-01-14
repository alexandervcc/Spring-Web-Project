package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.GamesDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Games;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class GamesMapper {
    private final ModelMapper mapper;

    public GamesDto convertToDto(Games Games) {
        GamesDto GamesDto = mapper.map(Games, GamesDto.class);
        return GamesDto;
      }
    
      public Games convertToEntity(GamesDto GamesDto) {
        Games Games = mapper.map(GamesDto, Games.class);
        return Games;
      }
}
