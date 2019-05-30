package exception;

public class ConnectorException extends RuntimeException {

    public ConnectorException() {
        super();
    }

    public ConnectorException(String message) {
        super(message);
    }

    public ConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectorException(Throwable cause) {
        super(cause);
    }
}
