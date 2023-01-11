package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.services.SucursalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
class SucursalController {
	private final SucursalService sucursalService;

	@GetMapping(value = "/sucursal/getAll")
	public String getAllSucursal(Model model) {
		List<SucursalDTO> list = this.sucursalService.getAllSucursal();
		model.addAttribute("list", list);
		return "home";
	}

	@GetMapping(value = "/sucursal/add")
	public String getNewSucursalPage(Model model) {
		SucursalDTO sucursal = new SucursalDTO();
		model.addAttribute("sucursal", sucursal);
		return "create";
	}

	@PostMapping(value = "/sucursal/add")
	public String createNewSucursal(SucursalDTO sucursalDto, Model model) {
		this.sucursalService.createNew(sucursalDto);
		return "redirect:/sucursal/getAll";
	}

	@GetMapping(value = "/sucursal/update/{id}")
	public String getUpdateSucursalPage(@PathVariable(value = "id") Integer idSucursal, Model model) {
		SucursalDTO sucursal = this.sucursalService.getOne(idSucursal);
		model.addAttribute("sucursal", sucursal);
		return "update";
	}
	
	@PutMapping(value = "/sucursal/update")
	public String updateSucursal(SucursalDTO sucursalDto, Model model) {
		log.error(sucursalDto.toString());
		this.sucursalService.update(sucursalDto);
		return "redirect:/sucursal/getAll";
	}
	

	@GetMapping(value = "/sucursal/getOne/{id}")
	public String getOneSucursal(@PathVariable(value = "id") Integer idSucursal, Model model) {
		SucursalDTO sucursal = this.sucursalService.getOne(idSucursal);
		model.addAttribute("sucursal", sucursal);
		return "getOne";
	}

	@DeleteMapping(value = "/sucursal/delete/{id}")
	public String deleteOneSucursal(@PathVariable(value = "id") Integer idSucursal) {
		this.sucursalService.deleteOne(idSucursal);
		return "redirect:/sucursal/getAll";
	}

}
