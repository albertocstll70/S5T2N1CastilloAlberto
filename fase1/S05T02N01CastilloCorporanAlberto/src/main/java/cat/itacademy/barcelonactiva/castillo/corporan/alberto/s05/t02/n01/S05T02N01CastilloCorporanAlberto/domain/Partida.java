package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "partida")
public class Partida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = false, updatable = false)
	private int idPartida;

	@Column(name = "id_jugador", insertable = false, updatable = false)
	private int idJugador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jugador")
	private Jugador jugador;

	public Partida() {

	}

	public Partida(int idJugador) {

		this.idJugador = idJugador;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

}
