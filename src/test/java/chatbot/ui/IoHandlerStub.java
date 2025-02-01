package chatbot.ui;

/**
 * A stub for IoHandler that allows for testing of chatbot commands.
 *
 * @author Jovin Ang
 */
public class IoHandlerStub implements IoHandler {
    /**
     * The last message sent by the chatbot.
     */
    private String lastMessageSent;

    /**
     * Constructs an IoHandlerStub.
     */
    public IoHandlerStub() {
        lastMessageSent = "";
    }

    @Override
    public void send(String message) {
        lastMessageSent = message;
    }

    /**
     * This method is not supported in IoHandlerStub.
     *
     * @throws UnsupportedOperationException This method is not supported in IoHandlerStub.
     */
    @Override
    public String getInput() {
        throw new UnsupportedOperationException("This method is not supported in IoHandlerStub.");
    }

    /**
     * Retrieves the last message sent by the chatbot.
     *
     * @return The last message sent by the chatbot.
     */
    public String getLastMessageSent() {
        return lastMessageSent;
    }
}
