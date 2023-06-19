package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.TiradaDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service.PartidaTiradaRestTemplateServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "http://localhost:9081")
@Controller
@RequestMapping("/")

public class PartidaTiradaController {

	@Autowired
	private PartidaTiradaRestTemplateServiceImpl partidaTiradaServiceImpl;

	// tiradas
	@Operation(summary = "Crea tirada y partida", description = "Crea una nueva partida y tirada", tags = {
			"PartidaTiradaController" })
	@PostMapping(value = "/players/{id}/games")
	public String jugar(@ModelAttribute("jugador") JugadorDTO jugador, String idJugador, Model model) {
		idJugador = jugador.getId();
		TiradaDTO tDTO = partidaTiradaServiceImpl.crearPartidaTirada(idJugador);
		model.addAttribute("tirada", tDTO);
		return "tiradaDados";
	}

	// Eliminar tiradas de un jugador
	@Operation(summary = "Elimina tiradas", description = "Elimina todas las tiradas de un jugador", tags = {
			"PartidaTiradaController" })
	@GetMapping(value = "/players/{id}/games")
	public String borrarTirada(@ModelAttribute("jugador") Jugador jugador, Model model) {

		String repuesta = partidaTiradaServiceImpl.deleteTiradas(jugador.getId());

		model.addAttribute("response", repuesta);

		return "vistaBorrarTiradasOk";

	}
@Operation(summary = "Tiradas", description = "Muestra todas las tiras de un jugador", tags = {
			"PartidaTiradaController" })
	@GetMapping(value = "/players/{id}/games/historico")
	public String listarJugadas(@ModelAttribute("jugador") JugadorDTO jugador, Model model) {
		String idJugador = jugador.getId();
		List<TiradaDTO> listTiradas = partidaTiradaServiceImpl.listPartidaJugador(idJugador);
		model.addAttribute("listTiradas", listTiradas);


		return "VistaTiradas";

		

	}
	
}