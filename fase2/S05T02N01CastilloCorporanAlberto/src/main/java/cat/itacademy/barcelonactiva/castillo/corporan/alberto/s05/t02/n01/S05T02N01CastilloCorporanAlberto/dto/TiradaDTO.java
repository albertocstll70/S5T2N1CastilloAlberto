package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto;

public class TiradaDTO {
	private String id;

	private String idJugador;

	private String idPartida;

	private int valorDado1;

	private int valorDado2;

	private boolean resultado;

	private String resultadoPartida;

	public TiradaDTO() {

	}

	public void calResultado() {
		if (resultado) {
			this.resultadoPartida = "Partida ganada";

		} else {
			this.resultadoPartida = "Partida perdida";
		}

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

	public String getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
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

	public boolean isResultado() {
		calResultado();
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getResultadoPartida() {
		return resultadoPartida;
	}

	public void setResultadoPartida(String resultadoPartida) {
		this.resultadoPartida = resultadoPartida;
	}

	@Override
	public String toString() {
		return "TiradaDTO [idTirada=" + id + ", idJugador=" + idJugador + ", idPartida=" + idPartida
				+ ", valorDado1=" + valorDado1 + ", valorDado2=" + valorDado2 + ", resultado=" + resultado
				+ ", resultadoPartida=" + resultadoPartida + "]";
	}

}
