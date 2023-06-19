package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain;


public class Partida {
	
	private String id;

	private String idJugador;
	
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
