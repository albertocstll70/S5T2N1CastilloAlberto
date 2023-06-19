package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Jugador;

@EnableMongoRepositories
public interface JugadorRepository extends MongoRepository<Jugador, String> {
	Boolean existsByNombre(String nombre);
	Optional<Jugador> findByNombre(String nombre);
	Optional<Jugador> findById(String id);

}
