package chatbot.exception;

/**
 * Checked exception for when there is an attempt to
 * toggle the state of the task to its current state.
 *
 * @author Jovin Ang
 */
public class IllegalTaskStateChangeException extends Exception {
    /**
     * Creates a IllegalTaskStateChangeException with the default message
     * "Unable to toggle to same state".
     */
    public IllegalTaskStateChangeException(String task, String prevState, String newState) {
        super("Unable to change \"" + task + "\" from \"" + prevState + "\" to \"" + newState + "\"");
    }
}
