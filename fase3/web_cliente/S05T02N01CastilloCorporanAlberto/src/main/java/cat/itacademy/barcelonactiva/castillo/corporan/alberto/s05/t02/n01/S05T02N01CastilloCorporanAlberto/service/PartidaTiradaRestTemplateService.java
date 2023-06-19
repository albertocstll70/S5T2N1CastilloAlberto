package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.service;

import java.util.List;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.TiradaDTO;

public interface PartidaTiradaRestTemplateService {
	TiradaDTO crearPartidaTirada(String idJugador);

	String  deleteTiradas(String idJugador);

	List<TiradaDTO> listPartidaJugador(String id);

}
