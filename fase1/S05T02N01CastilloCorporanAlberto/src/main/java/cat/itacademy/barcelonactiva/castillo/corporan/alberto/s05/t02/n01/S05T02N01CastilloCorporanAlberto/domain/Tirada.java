package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain;

import java.util.Random;

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
@Table(name = "tirada")
public class Tirada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tirada", insertable = false, updatable = false)
	private int idTirada;

	@Column(name = "id_jugador", insertable = false, updatable = false)
	private int idJugador;

	@Column(name = "id_partida", insertable = false, updatable = false)
	private int idPartida;

	@Column(name = "valor_dado_1", nullable = false, length = 2)
	private int valorDado1;

	@Column(name = "valor_dado_2", nullable = false, length = 2)
	private int valorDado2;

	@Column(name = "resultado", nullable = false, length = 2)
	private boolean resultado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jugador")
	private Jugador jugador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_partida")
	private Partida partida;

	public Tirada() {
		numberDado();

	}

	public Tirada(int idJugador, int idPartida) {
		this.idJugador = idJugador;
		this.idPartida = idPartida;
		numberDado();

	}

	public int getIdTirada() {
		return idTirada;
	}

	public void setIdTirada(int idTirada) {
		this.idTirada = idTirada;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getValorDado1() {
		return valorDado1;
	}

	public void setValorDado1(int valorDado1) {
		this.valorDado1 = valorDado1;
	}

	public int getValorDado2() {
		return valorDado2;
	}

	public void setValorDado2(int valorDado2) {
		this.valorDado2 = valorDado2;
	}

	public boolean getResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public void numberDado() {
		Random random = new Random();
		int valorDado1 = random.nextInt(6) + 1;
		int valorDado2 = random.nextInt(6) + 1;
		setValorDado1(valorDado1);
		setValorDado2(valorDado2);

		if (valorDado1 + valorDado2 == 7) {
			this.resultado = true;

		}
	}

	@Override
	public String toString() {
		return "Tirada [idTirada=" + idTirada + ", idJugador=" + idJugador + ", idPartida=" + idPartida
				+ ", valorDado1=" + valorDado1 + ", valorDado2=" + valorDado2 + ", resultado=" + resultado
				+ ", jugador=" + jugador + ", partida=" + partida + "]";
	}

}
