package exceptions;

@SuppressWarnings("serial")
public class ExpenseNotFoundException extends Exception {
	
	public ExpenseNotFoundException(String message) {
        super(message);
    }

	public ExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
