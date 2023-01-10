package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {
	private Integer pk_SucursalID;
	private String nombreSucursal;
	private	String paisSucursal;
	private String tipoSucursal;
}
