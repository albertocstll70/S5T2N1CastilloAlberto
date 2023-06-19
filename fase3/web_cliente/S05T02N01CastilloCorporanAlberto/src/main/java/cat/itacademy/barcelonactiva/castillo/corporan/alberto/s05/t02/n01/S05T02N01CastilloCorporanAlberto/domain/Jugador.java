package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain;

import java.util.Date;

public class Jugador {

	private String id;
	private String nombre;
	private String password;
	private Date fechaCreacion;

	public Jugador(String nomJugador, String password) {
		this.nombre = nomJugador;
		this.password = password;
		this.fechaCreacion = new Date();

	}

	public Jugador() {
		this.fechaCreacion = new Date();

	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String idJugador) {
		this.id = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Jugador [idJugador=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + "]";
	}

}
