package foreignExchange.exception;

public class NoCurrencyMappedException extends RuntimeException {
    public NoCurrencyMappedException(String message) {
        super(message);
    }
}
