package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.mapper.SucursalMapper;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.repository.SucursalRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SucursalServiceImpl implements SucursalService {
	private final SucursalRepository sucursalRepository;
	private final SucursalMapper sucursalMapper;

	@Override
	public List<SucursalDTO> getAllSucursal() {
		List<SucursalDTO> listDto = this.sucursalRepository.findAll()
				.stream()
				.map(s -> this.sucursalMapper.convertToDto(s))
				.collect(Collectors.toList());
		return listDto;
	}

	@Override
	public SucursalDTO createNew(SucursalDTO sucursalDTO) {
		Sucursal newSucursal = Sucursal.builder()
				.nombreSucursal(sucursalDTO.nombreSucursal)
				.paisSucursal(sucursalDTO.paisSucursal)
				.build();
		return this.sucursalMapper.convertToDto(this.sucursalRepository.save(newSucursal));
	}

	@Override
	public Sucursal update(SucursalDTO sucursalDTO) {
		Optional<Sucursal> sucursalOptional = this.sucursalRepository.findById(sucursalDTO.pk_SucursalID);
		if (sucursalOptional.isEmpty()) {
			return null;
		}
		Sucursal sucursal = sucursalOptional.get();
		sucursal.setNombreSucursal(sucursalDTO.nombreSucursal);
		sucursal.setPaisSucursal(sucursalDTO.paisSucursal);
		return this.sucursalRepository.save(sucursal);
	}

	@Override
	public SucursalDTO getOne(Integer idSucursal) {
		Sucursal s = this.sucursalRepository.findById(idSucursal).get();
		if (s == null) {
			return null;
		}

		return this.sucursalMapper.convertToDto(s);
	}

	@Override
	public Sucursal deleteOne(Integer idSucursal) {
		Optional<Sucursal> sucursalOptional = this.sucursalRepository.findById(idSucursal);
		if (sucursalOptional.isEmpty()) {
			return null;
		}
		this.sucursalRepository.deleteById(idSucursal);
		return sucursalOptional.get();
	}
}
