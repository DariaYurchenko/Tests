package exception;

public class MailsSenderException extends RuntimeException {

    public MailsSenderException() {
        super();
    }

    public MailsSenderException(String message) {
        super(message);
    }

    public MailsSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailsSenderException(Throwable cause) {
        super(cause);
    }
}
