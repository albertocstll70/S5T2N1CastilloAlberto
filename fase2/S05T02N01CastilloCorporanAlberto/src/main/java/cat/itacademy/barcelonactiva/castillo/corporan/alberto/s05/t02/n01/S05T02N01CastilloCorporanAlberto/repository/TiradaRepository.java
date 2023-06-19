package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Tirada;
@EnableMongoRepositories
public interface TiradaRepository extends MongoRepository<Tirada, String> {
	@EntityGraph(attributePaths = "jugador")
	List<Tirada> findByIdJugador(String idJugador);	
}
