package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.services;

import java.util.List;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto.SucursalDTO;

public interface SucursalService {
	List<SucursalDTO> getAllSucursal();
	SucursalDTO createNew(SucursalDTO sucursalDTO);
	Sucursal update(SucursalDTO sucursalDTO);
	SucursalDTO getOne(Integer idSucursal);
	Sucursal deleteOne(Integer idSucursal);
}
