package ro.itschool.store_management.exception;

// This is a custom exception that will be thrown when the payload is invalid.
public class InvalidPayloadException extends RuntimeException {

    public InvalidPayloadException(String message) {
        super(message);
    }

}
