package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.config;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Tirada;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.JugadorDTO;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository.TiradaRepository;

@Configuration
public class JugadorConfigMap {

	@Autowired
	private TiradaRepository tiradaRepository;

	@Autowired
	private ModelMapper modelMapper;

	// Mapea jugador y JugadorDTO y calcula el porcentaje éxito
	public JugadorDTO convertToDto(Jugador jugador) {
		JugadorDTO dto = modelMapper.map(jugador, JugadorDTO.class);
		List<Tirada> listTiradasIdJugador = tiradaRepository.findByJugadorIdJugador(jugador.getIdJugador());
		double porcentajeExito = 0;

		int aux = 0;
		int aux2 = 0;

		if (listTiradasIdJugador.size() != 0) {
			for (Tirada t : listTiradasIdJugador) {
				aux++;
				if (t.getResultado()) {
					aux2++;
				}
			}
			porcentajeExito = (aux2 * 100) / aux;

		}
		System.out.println(porcentajeExito);
		dto.setPorcentajeExito(porcentajeExito);

		return dto;

	}

	public Jugador convertToEntity(JugadorDTO dto) {
		return modelMapper.map(dto, Jugador.class);
	}

	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setSkipNullEnabled(true)
				.setAmbiguityIgnored(true);
		return modelMapper;
	}

}
