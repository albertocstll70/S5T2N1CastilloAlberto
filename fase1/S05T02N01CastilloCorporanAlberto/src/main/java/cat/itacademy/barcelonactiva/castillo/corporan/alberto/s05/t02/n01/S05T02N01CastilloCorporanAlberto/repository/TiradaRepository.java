package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain.Tirada;
import jakarta.transaction.Transactional;

@Transactional
public interface TiradaRepository extends JpaRepository<Tirada, Integer> {
	@EntityGraph(attributePaths = "jugador")
	List<Tirada> findByJugadorIdJugador(Integer idJugador);

}
