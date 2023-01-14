package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto.PlayerDto;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model.Player;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PlayerMapper {
    private final ModelMapper mapper;

    public PlayerDto convertToDto(Player player) {
        PlayerDto playerDto = mapper.map(player, PlayerDto.class);
        return playerDto;
      }
    
      public Player convertToEntity(PlayerDto playerDTO) {
        Player player = mapper.map(playerDTO, Player.class);
        return player;
      }
}
