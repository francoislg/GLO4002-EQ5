package ca.ulaval.glo4002.GRAISSE.application.service.shared;

public class DoubleServiceRegistrationException extends RuntimeException {

    public DoubleServiceRegistrationException(Class<?> service) {
        super("A implementation for the service '" + service.getCanonicalName() + "' is already present.");
    }

    private static final long serialVersionUID = 1L;

}