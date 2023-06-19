package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain;

import java.util.Random;

public class Tirada {

	private String id;

	private String idJugador;

	private String idPartida;

	private int valorDado1;

	private int valorDado2;

	private boolean resultado;

	public Tirada() {
		numberDado();
	}

	public Tirada(String idJugador, String idPartida) {
		this.idJugador = idJugador;
		this.idPartida = idPartida;
		numberDado();
	}

	public String getId() {
		return id;
	}

	public void setId(String idTirada) {
		this.id = idTirada;
	}

	public String getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(String idJugador) {
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

	public String getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
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
		return "Tirada [idTirada=" + id + ", idJugador=" + idJugador + ", idPartida=" + idPartida
				+ ", valorDado1=" + valorDado1 + ", valorDado2=" + valorDado2 + ", resultado=" + resultado + "]";
	}


}
