package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.config.JugadorConfigMap;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.JugadorRepository;

@Service
public class JugadorServiceImpl implements JugadorService {

	@Autowired
	private JugadorRepository jugadorRepository;

	@Autowired
	private JugadorConfigMap mapJugador;

	@Override
	public Jugador guardarJugador(Jugador jugador) {
		if (jugadorRepository.existsByNombre(jugador.getNombre())) {
			throw new RuntimeException("Ya existe un jugador con el mismo nombre");
		}
		return jugadorRepository.save(jugador);

	}

	@Override
	public Jugador updateJugador(Jugador jugador) {
		return jugadorRepository.save(jugador);
	}

	@Override
	public Jugador buscarJugador(Integer id) {
		return jugadorRepository.findById(id).orElse(null);

	}

//	@Override
//	public void borrarJugador(Integer id) {
//		jugadorRepository.deleteById(id);
//
//	}

	@Override
	public List<JugadorDTO> getJuadoresPorc() {

		JugadorDTO jDTO = new JugadorDTO();
		List<Jugador> listJugadores = jugadorRepository.findAll();
		List<JugadorDTO> listJugadoresDTO = new ArrayList<JugadorDTO>();

		for (Jugador j : listJugadores) {
			jDTO = mapJugador.convertToDto(j);
			listJugadoresDTO.add(jDTO);

		}

		return listJugadoresDTO;
	}

	@Override
	public List<Jugador> jugadores() {
		return jugadorRepository.findAll();

	}

	@Override
	public HashMap<String, Double> rankingMedio() {

		double valorRankingMedio;
		HashMap<String, Double> rankingMedio = new HashMap<String, Double>();
		List<JugadorDTO> listJugadoresDTO = listJugador();

		double aux = 0;
		for (JugadorDTO j : listJugadoresDTO) {
			aux = aux + j.getPorcentajeExito();
		}

		valorRankingMedio = aux / listJugadoresDTO.size();

		rankingMedio.put("Rankig Medio: ", valorRankingMedio);

		return rankingMedio;
	}

	@Override
	public JugadorDTO rankingLoser() {
		List<JugadorDTO> listJugadorDTO = listJugador();
		Collections.sort(listJugadorDTO);

		return Collections.min(listJugadorDTO);
	}

	@Override
	public JugadorDTO rankingWinner() {
		List<JugadorDTO> listJugadorDTO = listJugador();
		Collections.sort(listJugadorDTO);

		return Collections.max(listJugadorDTO);
	}

	public List<JugadorDTO> listJugador() {

		JugadorDTO jDTO = new JugadorDTO();
		List<Jugador> listJugadores = jugadorRepository.findAll();
		List<JugadorDTO> listJugadoresDTO = new ArrayList<JugadorDTO>();

		for (Jugador j : listJugadores) {

			jDTO = mapJugador.convertToDto(j);
			listJugadoresDTO.add(jDTO);
		}

		return listJugadoresDTO;

	}
}
