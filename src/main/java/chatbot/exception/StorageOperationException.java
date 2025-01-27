package chatbot.exception;

/**
 * Checked exception for when an error occurs during storage operations.
 */
public class StorageOperationException extends Exception {
    /**
     * Constructs a new StorageOperationException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public StorageOperationException(String message) {
        super(message);
    }
}
