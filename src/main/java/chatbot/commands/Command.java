package chatbot.commands;

/**
 * Represents an abstract base class for commands that can be executed with provided arguments.
 * Subclasses must implement specific command behavior by overriding the execute method.
 *
 * @author Jovin Ang
 */
public abstract class Command {
    /**
     * Executes the defined logic for the command with the provided arguments.
     * The behavior of this method should be implemented by specific command classes.
     *
     * @param arguments The arguments passed to the command for execution.
     */
    public abstract void execute(String arguments);
}
