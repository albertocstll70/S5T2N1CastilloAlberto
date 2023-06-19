package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.controllers;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service.JugadorRestTemplateService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping("/")

public class JugadorRestTemplateController {

	@Autowired
	private JugadorRestTemplateService jugadorServiceImpl;

	// inicio

	@Operation(summary = "Inicio", description = "Inicio", tags = { "jugadorController" })
	@GetMapping("/inicio")
	public String welcome() {
		return "inicio";
	}

	@Operation(summary = "Info", description = "Juego de dados", tags = { "jugadorController" })
	@GetMapping("/inicio/info")
	public String info() {
		return "info";
	}

	// crear
	@Operation(summary = "formulario", description = "Crear jugador", tags = { "jugadorController" })

	@GetMapping(value = "/players/register")
	public String addJugador(Model model) {
		try {
			model.addAttribute("jugador", new Jugador());

		} catch (DataAccessException e) {

		}
		return "introducirDatos";

	}

	// crear
	@Operation(summary = "Guardar", description = "Guarda la entidad jugadro", tags = { "jugadorController" })
	@PostMapping(value = "/players/register")
	public String guardarJugador(@ModelAttribute("jugador") Jugador jugador) {

		boolean response = jugadorServiceImpl.guardarJugador(jugador);

		if (response) {
			return "login";
		} else {
			return "error";

		}

	}

	// update nombre
	@Operation(summary = "Update", description = "Actualiza el nombre del jugador", tags = { "jugadorController" })
	@GetMapping(value = "players/update")
	public String updateName(Model model) {
		model.addAttribute("jugadorNewNombre", new Jugador());
		return "introducirNombre";
	}

	@Operation(summary = "Update", description = "Actualiza el nombre del jugador", tags = { "jugadorController" })
	@PutMapping(value = "/players")
	public String updateJugador(@SessionAttribute("jugador") JugadorDTO jugador,
		@ModelAttribute("jugadorNewNombre") Jugador jugadorNewNombre, Model model) {
		jugadorNewNombre.setId(jugador.getId());
		JugadorDTO jDTO = jugadorServiceImpl.updateJugador(jugadorNewNombre);

		if (jDTO != null) {
			model.addAttribute("jugadorNewNombre", jDTO);
			return "jugador";
		} else {

			return "error";
		}
	}

	// mostrar jugadores
	@Operation(summary = "Muestra", description = "Lista todos los jugadores", tags = { "jugadorController" })
	@GetMapping(value = "/players")
	public String getAll(Model model) {

		JugadorDTO jugadoresDTO[] = jugadorServiceImpl.jugadores();
		model.addAttribute("jugadores", jugadoresDTO);
		return "jugadores";
	}

	@Operation(summary = "Muestra", description = "Muestra el porcentaje medio de éxito de los jugadores", tags = {
			"jugadorController" })
	@GetMapping("/players/ranking")
	public String rankingMedio(Model model) {
		HashMap<String, Double> media = jugadorServiceImpl.rankingMedio();

		model.addAttribute("ranking", media);
		return "vistaRanking";

	}

	@Operation(summary = "Muestra", description = "Muestra el jugador con peor porcentaje de éxito", tags = {
			"jugadorController" })
	@GetMapping("/players/ranking/loser")
	public String rankingLoser(Model model) {

		HashMap<String, JugadorDTO> rankingWinner = new HashMap<String, JugadorDTO>();
		JugadorDTO jugadorW = jugadorServiceImpl.rankingLoser();
		rankingWinner.put("Jugador con peor Porcentaje es: ", jugadorW);

		model.addAttribute("ranking", rankingWinner);

		return "vistaRankingWL";

	}

	@Operation(summary = "Muestra", description = "Muestra el jugador con mejor porcentaje de éxito", tags = {
			"jugadorController" })
	@GetMapping("/players/ranking/winner")
	public String rankingWinner(Model model) {

		HashMap<String, JugadorDTO> rankingWinner = new HashMap<String, JugadorDTO>();
		JugadorDTO jugadorW = jugadorServiceImpl.rankingWinner();
		rankingWinner.put("Jugador con mejor Porcentaje es:   ", jugadorW);

		model.addAttribute("ranking", rankingWinner);

		return "vistaRankingWL";

	}


}
