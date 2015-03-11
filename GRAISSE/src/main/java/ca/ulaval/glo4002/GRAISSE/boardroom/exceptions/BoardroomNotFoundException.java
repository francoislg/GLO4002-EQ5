package ca.ulaval.glo4002.GRAISSE.boardroom.exceptions;

public class BoardroomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6637349484013524170L;

	public BoardroomNotFoundException(String message) {
		super(message);
	}
}
