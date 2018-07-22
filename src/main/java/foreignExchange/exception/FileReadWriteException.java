package foreignExchange.exception;

public class FileReadWriteException extends RuntimeException {
    public FileReadWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
