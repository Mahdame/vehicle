package dev.maira.vehicle.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4144420131959193346L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
