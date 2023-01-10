package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.repository.SucursalRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SucursalServiceImpl implements SucursalService {
	private final SucursalRepository sucursalRepository;

	@Override
	public List<Sucursal> getAllSucursal() {
		return this.sucursalRepository.findAll();
	}

	@Override
	public Sucursal createNew(SucursalDTO sucursalDTO) {
		Sucursal newSucursal = Sucursal.builder()
				.nombreSucursal(sucursalDTO.getNombreSucursal())
				.paisSucursal(sucursalDTO.getPaisSucursal())
				.build();
		return this.sucursalRepository.save(newSucursal);
	}

	@Override
	public Sucursal update(SucursalDTO sucursalDTO) {
		Optional<Sucursal> sucursalOptional = this.sucursalRepository.findById(sucursalDTO.getPk_SucursalID());
		if(sucursalOptional.isEmpty()) {
			return null;
		}
		Sucursal sucursal = sucursalOptional.get();
		sucursal.setNombreSucursal(sucursalDTO.getNombreSucursal());
		sucursal.setPaisSucursal(sucursalDTO.getPaisSucursal());
		return this.sucursalRepository.save(sucursal);
	}

	@Override
	public Sucursal getOne(Integer idSucursal) {
		return this.sucursalRepository.findById(idSucursal).get();
	}

	@Override
	public Sucursal deleteOne(Integer idSucursal) {
		Optional<Sucursal> sucursalOptional = this.sucursalRepository.findById(idSucursal);
		if(sucursalOptional.isEmpty()) {
			return null;
		}
		this.sucursalRepository.deleteById(idSucursal);
		return sucursalOptional.get();
	}
}
