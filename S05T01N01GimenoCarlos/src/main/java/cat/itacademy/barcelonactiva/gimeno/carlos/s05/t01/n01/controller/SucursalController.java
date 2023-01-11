package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.gimeno.carlos.s05.t01.n01.services.SucursalService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
class SucursalController {
	private final SucursalService sucursalService;

	@GetMapping(value = "/sucursal/getAll")
	public String getAllSucursal(Model model) {
		List<Sucursal> list = this.sucursalService.getAllSucursal();
		model.addAttribute("list", list); 
		return "home";
	}

	@GetMapping(value="/sucursal/add")
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

	@PutMapping(value = "/sucursal/update")
	public String updateSucursal() {
		return "getAll";
	}

	@GetMapping(value = "/sucursal/getOne/{id}")
	public String getOneSucursal(@PathParam(value = "id") Long idSucursal) {
		return "getAll";
	}

	@DeleteMapping(value = "/sucursal/delete/{id}")
	public String deleteOneSucursal(@PathParam(value = "id") Long idSucursal) {
		return "getAll";
	}

}
