package be.multimedi.lessons.springadvanced.beers.exceptions;

public class InvalidBeerException extends RuntimeException {
    public InvalidBeerException() {
        super();
    }

    public InvalidBeerException(String message) {
        super(message);
    }

    public InvalidBeerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBeerException(Throwable cause) {
        super(cause);
    }
}
