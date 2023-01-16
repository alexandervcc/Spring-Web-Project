package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {
    public String id;
    public String nombre;
    public Date fechaRegistro;
    public String password;
    public Double percentage;
    public List<GamesDto> listGames;
}
