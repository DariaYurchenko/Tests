package exception;

public class MailsSenderRuntimeException extends RuntimeException {

    public MailsSenderRuntimeException() {
        super();
    }

    public MailsSenderRuntimeException(String message) {
        super(message);
    }

    public MailsSenderRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailsSenderRuntimeException(Throwable cause) {
        super(cause);
    }
}
