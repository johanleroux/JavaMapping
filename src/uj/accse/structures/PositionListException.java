package uj.accse.structures;

/**
 * An exception that will be thrown if there is an error with the Position List
 *
 */
public class PositionListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PositionListException() {
		super();
	}

	public PositionListException(String message) {
		super(message);
	}

	public PositionListException(String message, Throwable cause) {
		super(message, cause);
	}

	public PositionListException(Throwable cause) {
		super(cause);
	}

}
