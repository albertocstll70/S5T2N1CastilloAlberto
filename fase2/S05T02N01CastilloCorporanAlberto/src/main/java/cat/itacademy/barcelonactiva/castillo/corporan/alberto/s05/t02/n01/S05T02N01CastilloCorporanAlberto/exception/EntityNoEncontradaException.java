package cat.itacademy.barcelonactiva.castillo.corporan.alberto.s05.t02.n01.S05T02N01CastilloCorporanAlberto.exception;

public class EntityNoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntityNoEncontradaException(String mensaje) {
		super(mensaje);
	}

}