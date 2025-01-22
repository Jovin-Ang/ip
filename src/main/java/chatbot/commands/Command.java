package chatbot.commands;

/**
 * Represents a command that can be executed with given arguments.
 * Implementing classes should define the specific behavior
 * for the execute method.
 *
 * @author Jovin Ang
 */
public interface Command {
    /**
     * Executes the defined logic for the command with the provided arguments.
     * The behavior of this method should be implemented by specific command classes.
     *
     * @param arguments The arguments passed to the command for execution.
     */
    void execute(String arguments);
}
