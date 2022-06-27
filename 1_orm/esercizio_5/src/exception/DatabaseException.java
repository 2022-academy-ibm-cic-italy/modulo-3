package exception;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseException(final Throwable cause) {
        super(cause);
    }
}