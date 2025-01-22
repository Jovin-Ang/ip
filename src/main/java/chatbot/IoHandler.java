package chatbot;

/**
 * An interface for handling input and output operations.
 * Provides methods to send messages and retrieve input.
 *
 * @author Jovin Ang
 */
public interface IoHandler {
    /**
     * Sends a message.
     *
     * @param message The message to be sent.
     */
    void send(String message);

    /**
     * Reads and retrieves the user's input.
     *
     * @return A string representing the user's input.
     */
    String getInput();
}
