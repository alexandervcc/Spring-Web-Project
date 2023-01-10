package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "tbl_sucursal")
public class Sucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//estrategia de generacion del ID
	@Column(name = "id")
	private Integer pk_SucursalID;

	@Column(name = "nombre")
	private String nombreSucursal;
	
	@Column(name = "pais")
	private	String paisSucursal;
}
