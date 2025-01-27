package chatbot.exception;

/**
 * Checked exception for when a command receives arguments
 * in an incorrect format.
 *
 * @author Jovin Ang
 */
public class InvalidCommandSyntaxException extends Exception {
    /**
     * Constructs a new InvalidCommandSyntaxException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidCommandSyntaxException(String message) {
        super(message);
    }
}
