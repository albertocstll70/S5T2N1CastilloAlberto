package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.config.TiradaConfigMap;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Partida;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Tirada;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.TiradaDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.PartidaRepository;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.TiradaRepository;

@Service
public class PartidaTiradaServiceImpl implements PartidaTiradaService {

	@Autowired
	private PartidaRepository partidaRepository;

	@Autowired
	private TiradaRepository tiradaRepository;

	@Autowired
	private TiradaConfigMap tiradaMap;

	@Transactional
	@Override
	public List<TiradaDTO> listPartidaJugador(String id) {
		List<Tirada> listTirada = tiradaRepository.findByIdJugador(id);
		List<TiradaDTO> listTiradaDTO = new ArrayList<TiradaDTO>();
		for (Tirada t : listTirada) {
			listTiradaDTO.add(tiradaMap.convertToDto(t));
		}

		return listTiradaDTO;

	}

	@Transactional
	@Override
	public Tirada crearPartidaTirada(Partida partida, Tirada tirada) {
		partidaRepository.save(partida);
		tirada.setIdJugador(partida.getIdJugador());

		tirada.setIdPartida(partida.getId());
		Tirada newTirada = tiradaRepository.save(tirada);
		return newTirada;
	}

	@Transactional
	@Override
	public Boolean deleteTiradas(String idJugador) {

		List<Tirada> listTiradas = new ArrayList<Tirada>();

		listTiradas = tiradaRepository.findByIdJugador(idJugador);

		if (listTiradas != null) {

			tiradaRepository.deleteAll(listTiradas);
			return true;
		} else {

			return false;
		}

	}

}
