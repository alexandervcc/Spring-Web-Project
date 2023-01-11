package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {
	public Integer pk_SucursalID;
	public String nombreSucursal;
	public String paisSucursal;
	public String tipoSucursal;
	private List<String> paisesUE = List.of("Alemania", "Bélgica", "Croacia", "Dinamarca", "España", "Francia", "Irlanda",
			"Letonia", "Luxemburgo", "Países Bajos", "Suecia", "Bulgaria", "Eslovaquia", "Estonia", "Grecia", "Malta",
			"Polonia", "República Checa", "Austria", "Chipre", "Eslovenia", "Finlandia", "Hungría", "Italia", "Lituania",
			"Portugal", "Rumanía");

	public void checkEUCountry() {
		if (paisSucursal != null) {
			if (this.paisesUE.contains(paisSucursal)) {
				this.tipoSucursal = "UE";
			} else {
				this.tipoSucursal = "Fuera UE";
			}
			return;
		}
		paisSucursal = "N/A";
	}
}
