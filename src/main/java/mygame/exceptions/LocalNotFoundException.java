package mygame.exceptions;

public class LocalNotFoundException extends Exception{
    private static final String LOCAL_NOT_FOUND = "Local not found";
    public LocalNotFoundException() {
    }

    public LocalNotFoundException(String message) {
        super(message);
    }
}
