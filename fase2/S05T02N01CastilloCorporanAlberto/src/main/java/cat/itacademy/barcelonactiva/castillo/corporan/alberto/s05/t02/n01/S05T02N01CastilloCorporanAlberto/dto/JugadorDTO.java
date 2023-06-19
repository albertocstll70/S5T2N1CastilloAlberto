package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.dto;

import java.util.Date;
import java.util.Objects;

public class JugadorDTO implements Comparable<JugadorDTO> {

	private String id;

	private String nombre;

	private Date fechaCreacion;

	private double porcentajeExito;

	public JugadorDTO() {

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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public double getPorcentajeExito() {
		return porcentajeExito;
	}

	public void setPorcentajeExito(double porcentajeExito) {
		this.porcentajeExito = porcentajeExito;
	}

	@Override
	public String toString() {
		return "JugadorDTO [idJugador=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion
				+ ", porcentajeExito=" + porcentajeExito + "]";
	}

	// CompareTO
	@Override
	public int compareTo(JugadorDTO jDTO) {
		if (this.porcentajeExito > jDTO.porcentajeExito) {
			return 1;
		} else if (this.porcentajeExito < jDTO.porcentajeExito) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		JugadorDTO jDTO = (JugadorDTO) o;
		return porcentajeExito == jDTO.porcentajeExito && Objects.equals(nombre, jDTO.nombre);
	}

}
