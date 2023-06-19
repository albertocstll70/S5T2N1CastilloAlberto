package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.HashMap;
import java.util.List;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;

public interface JugadorService {

	Jugador guardarJugador(Jugador jugador);

	JugadorDTO updateJugador(Jugador jugador);

	Jugador buscarJugador(String id);

	// void borrarJugador(Integer id);

	List<JugadorDTO> getJuadoresPorc();

	List<JugadorDTO> jugadores();

	HashMap<String, Double> rankingMedio();

	JugadorDTO rankingLoser();

	JugadorDTO rankingWinner();

	Boolean existsByNombre(String nombre);

	JugadorDTO findByNombre(String nombre);

}
