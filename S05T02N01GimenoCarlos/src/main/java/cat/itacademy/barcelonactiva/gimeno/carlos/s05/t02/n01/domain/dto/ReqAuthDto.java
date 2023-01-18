package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqAuthDto {
    @NotNull(message = "Ingrese un valor de usuario")
    public String name;
    @NotNull(message = "Ingrese un valor de contrasenia")
    public String password;
}
