package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.services;

import java.util.List;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto.SucursalDTO;

public interface SucursalService {
	List<Sucursal> getAllSucursal();
	Sucursal createNew(SucursalDTO sucursalDTO);
	Sucursal update(SucursalDTO sucursalDTO);
	Sucursal getOne(Integer idSucursal);
	Sucursal deleteOne(Integer idSucursal);
}
