package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Tirada;
import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto.TiradaDTO;

@Configuration
@Component
public class TiradaConfigMap {

	public TiradaDTO convertToDto(Tirada tirada) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(tirada, TiradaDTO.class);
	}

	public Tirada convertToEntity(TiradaDTO dto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(dto, Tirada.class);
	}

}
