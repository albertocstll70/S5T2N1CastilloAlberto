package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Partida;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Tirada;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.TiradaDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service.JugadorServiceImpl;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service.PartidaTiradaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class PartidaTiradaController {

	@Autowired
	private JugadorServiceImpl jugadorServiceImpl;

	@Autowired
	private PartidaTiradaServiceImpl partidaServiceImpl;

	// crear partida y tirada
	@Operation(summary = "Crea tirada y partida", description = "Crea una nueva partida y tirada", tags = {
			"PartidaTiradaController" })
	@PostMapping(value = "/players/{id}/games")
	public ResponseEntity<?> crearPartida(@PathVariable("id") Integer idJugador) {
		Jugador jugador = jugadorServiceImpl.buscarJugador(idJugador);

		if (jugador != null) {
			Partida partida = new Partida();
			partida.setIdJugador(idJugador);
			partida.setJugador(jugador);

			Tirada tirada = new Tirada();
			tirada.setJugador(jugador);
			partidaServiceImpl.crearPartidaTirada(partida, tirada);
			Map<Object, Object> partidaTirada = new HashMap<>();
			partidaTirada.put(partida, tirada);
			return new ResponseEntity<Map<Object, Object>>(partidaTirada, HttpStatus.CREATED);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Eliminar tiradas de un jugador

	@Operation(summary = "Tiradas", description = "Elimina todas las tiradas de un jugador", tags = {
			"PartidaTiradaController" })
	@DeleteMapping(value = "/players/{id}/games")
	public ResponseEntity<?> borrarTirada(@PathVariable("id") Integer idJugador) {
		Map<String, Object> respuesta = new HashMap<String, Object>();

		boolean dTiradas = partidaServiceImpl.deleteTiradas(idJugador);

		if (dTiradas) {
			respuesta.put("Se han borrado las tiradas", "OK");
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);

		}

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Operation(summary = "Tiradas", description = "Muestra todas las tiras de un jugador", tags = {
			"PartidaTiradaController" })
	@GetMapping(value = "/players/{id}/games")
	public ResponseEntity<?> listarJugadas(@PathVariable("id") Integer idJugador) {

		List<TiradaDTO> listTiradas = partidaServiceImpl.listPartidaJugador(idJugador);
		return new ResponseEntity<Object>(listTiradas, HttpStatus.OK);

	}
}
