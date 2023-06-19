package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Jugadores")
public class Jugador {
	@Id
	private String id;
	private String nombre;
	private Date fechaCreacion;

	public Jugador(String nomJugador) {
		this.nombre = nomJugador;
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

	@Override
	public String toString() {
		return "Jugador [idJugador=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion + "]";
	}

}
