package by.ttre16.briana.exception;

public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException() { }

    public IllegalRequestDataException(String message) {
        super(message);
    }
}
