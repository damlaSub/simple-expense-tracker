package exceptions;

@SuppressWarnings("serial")
public class InvalidExpenseException extends Exception {
	
	public InvalidExpenseException(String message) {
        super(message);
    }

	public InvalidExpenseException(String message, Throwable cause) {
        super(message, cause);
    }
}
