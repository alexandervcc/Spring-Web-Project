package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamesDto {
    public String id;
    public Integer value;
    public Boolean result;
}
