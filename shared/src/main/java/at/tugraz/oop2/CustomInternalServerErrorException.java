package at.tugraz.oop2;

public class CustomInternalServerErrorException extends RuntimeException {
    public CustomInternalServerErrorException(String message) {
        super(message);
    }
}