package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.ManyToOne;

@Document(collection = "Partidas")
public class Partida {
	@Id
	private String id;

	private String idJugador;
	@ManyToOne
	@DBRef
	@JsonIgnoreProperties("Partidas")
	private Jugador jugador;

	public Partida() {

	}

	public Partida(String idJugador) {
		this.idJugador = idJugador;
	}

	public String getId() {
		return id;
	}

	public void setId(String idPartida) {
		this.id = idPartida;
	}

	public String getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(String idJugador) {
		this.idJugador = idJugador;
	}

	@Override
	public String toString() {
		return "Partida [idPartida=" + id + ", idJugador=" + idJugador + "]";
	}

}
