package exception;

public class EncryptionRuntimeException extends RuntimeException {

    public EncryptionRuntimeException() {
        super();
    }

    public EncryptionRuntimeException(String message) {
        super(message);
    }

    public EncryptionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptionRuntimeException(Throwable cause) {
        super(cause);
    }
}
