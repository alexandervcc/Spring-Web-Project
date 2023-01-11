package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto.SucursalDTO;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SucursalMapper {
  private ModelMapper modelMapper;

  public SucursalDTO convertToDto(Sucursal sucursal) {
    SucursalDTO sucursalDto = modelMapper.map(sucursal, SucursalDTO.class);
    sucursalDto.checkEUCountry();
    return sucursalDto;
  }

  public Sucursal convertToEntity(SucursalDTO sucursalDTO) {
    Sucursal sucursal = modelMapper.map(sucursalDTO, Sucursal.class);
    return sucursal;
  }
}
