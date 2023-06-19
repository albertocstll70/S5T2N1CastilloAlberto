package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.HashMap;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;


public interface JugadorRestTemplateService {

	Boolean guardarJugador(Jugador jugador);

	JugadorDTO updateJugador(Jugador jugador);
	

	JugadorDTO[] jugadores();

	HashMap<String, Double> rankingMedio();

	JugadorDTO rankingLoser();

	JugadorDTO rankingWinner();

	

	

}
