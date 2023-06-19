package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.controllers;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.config.JugadorConfigMap;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.exception.EntityNoEncontradaException;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class JugadorController {

	@Autowired
	private JugadorService jugadorServiceImpl;

	@Autowired
	private JugadorConfigMap jugadorMap;

	// crear
	@Operation(summary = "jugador", description = "Crear jugador", tags = { "jugadorController" })
	@PostMapping(value = "/players")
	public Jugador crearJugador(@RequestBody Jugador j) {
		return jugadorServiceImpl.guardarJugador(j);

	}

	// update
	@Operation(summary = "jugador", description = "Actualiza el nombre de un jugador ", tags = { "jugadorController" })
	@PutMapping(value = "/players/{id}")
	public ResponseEntity<?> updateJugador(@RequestBody Jugador j, @PathVariable("id") String id)
			throws EntityNoEncontradaException {
		Jugador jugador = jugadorServiceImpl.buscarJugador(id);

		try {
			if (jugador == null) {
				throw new EntityNoEncontradaException("No se encontró ninguna entidad con el ID " + id);
			}

			jugador.setNombre(j.getNombre());
			jugadorServiceImpl.updateJugador(jugador);
			return new ResponseEntity<Object>(jugador, HttpStatus.CREATED);

		} catch (EntityNoEncontradaException e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Operation(summary = "jugador", description = "Lista los  todos los jugadores y sus datos ", tags = {
			"jugadorController" })
	@GetMapping(value = "/players")
	public ResponseEntity<?> getAll() {

		try {
			return new ResponseEntity<Object>(jugadorServiceImpl.getJuadoresPorc(), HttpStatus.OK);
		} catch (DataAccessException e) {
			e.getMessage();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@Operation(summary = "jugador", description = "Lista un jugador ", tags = { "jugadorController" })
	@GetMapping("/getOne/{id}")
	public JugadorDTO getEntity(@PathVariable("id") String entityId) {
		Jugador entity = jugadorServiceImpl.buscarJugador(entityId);
		JugadorDTO dto = jugadorMap.convertToDto(entity);
		return dto;
	}

	@Operation(summary = "jugador", description = "Lista el ranking medio ", tags = { "jugadorController" })
	@GetMapping("/players/ranking")
	public ResponseEntity<?> rankingMedio() {
		return new ResponseEntity<HashMap<String, Double>>(jugadorServiceImpl.rankingMedio(), HttpStatus.OK);

	}

	@Operation(summary = "jugador", description = "devuelve al jugador/a con peor porcentaje de éxito", tags = {
			"jugadorController" })
	@GetMapping("/players/ranking/loser")
	public ResponseEntity<?> rankingLoser() {

		return new ResponseEntity<JugadorDTO>(jugadorServiceImpl.rankingLoser(), HttpStatus.OK);

	}

	@Operation(summary = "jugador", description = "devuelve al jugador/a con mejor  porcentaje de éxito", tags = {
			"jugadorController" })
	@GetMapping("/players/ranking/winner")
	public ResponseEntity<?> rankingWinner() {

		return new ResponseEntity<JugadorDTO>(jugadorServiceImpl.rankingWinner(), HttpStatus.OK);

	}

}
