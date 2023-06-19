package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.config.TiradaConfigMap;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Partida;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Tirada;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.TiradaDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.PartidaRepository;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.TiradaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class PartidaTiradaServiceImpl implements PartidaTiradaService {

	@Autowired
	private PartidaRepository partidaRepository;

	@Autowired
	private TiradaRepository tiradaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TiradaConfigMap tiradaMap;

	@Transactional
	@Override
	public List<TiradaDTO> listPartidaJugador(Integer id) {
		List<Tirada> listTirada = tiradaRepository.findByJugadorIdJugador(id);
		List<TiradaDTO> listTiradaDTO = new ArrayList<TiradaDTO>();
		for (Tirada t : listTirada) {

			listTiradaDTO.add(tiradaMap.convertToDto(t));

		}

		return listTiradaDTO;

	}

	@Override
	public void crearPartidaTirada(Partida partida, Tirada tirada) {
		partidaRepository.save(partida);
		tirada.setIdJugador(partida.getIdJugador());
		tirada.setPartida(partida);
		tirada.setIdPartida(partida.getIdPartida());
		tiradaRepository.save(tirada);
	}

	@Transactional
	@Override
	public Boolean deleteTiradas(Integer idJugador) {

		List<Tirada> listTiradas = new ArrayList<Tirada>();

		listTiradas = tiradaRepository.findByJugadorIdJugador(idJugador);

		if (listTiradas != null) {

			tiradaRepository.deleteAll(listTiradas);
			return true;
		} else {

			return false;
		}

	}

}
