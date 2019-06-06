package exception;

public class ConnectorRuntimeException extends RuntimeException {

    public ConnectorRuntimeException() {
        super();
    }

    public ConnectorRuntimeException(String message) {
        super(message);
    }

    public ConnectorRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectorRuntimeException(Throwable cause) {
        super(cause);
    }
}
