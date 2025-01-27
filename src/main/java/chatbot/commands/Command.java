package chatbot.commands;

import chatbot.exception.InvalidCommandSyntaxException;

/**
 * Represents an abstract base class for commands that can be executed with provided arguments.
 * Subclasses must implement specific command behavior by overriding the execute method.
 *
 * @author Jovin Ang
 */
public abstract class Command {
    /**
     * The command trigger word or phrase.
     */
    private final String trigger;
    /**
     * A brief description of what the command does.
     */
    private final String description;
    /**
     * The command syntax.
     */
    private final String usage;

    /**
     * Constructs a Command with the specified trigger, description, and usage.
     *
     * @param trigger The command trigger word or phrase.
     * @param description A brief description of what the command does.
     * @param usage The command syntax.
     */
    public Command(String trigger, String description, String usage) {
        this.trigger = trigger;
        this.description = description;
        this.usage = usage;
    }

    /**
     * Executes the defined logic for the command with the provided arguments.
     * The behavior of this method should be implemented by specific command classes.
     *
     * @param arguments The arguments passed to the command for execution.
     * @throws InvalidCommandSyntaxException If the command arguments are in an incorrect format.
     */
    public abstract void execute(String arguments) throws InvalidCommandSyntaxException;

    /**
     * Returns the usage information for the command.
     *
     * @return A string containing the usage instructions and description of the command.
     */
    public String getCommandUsage() {
        return "`" + this.usage + "` " + this.description;
    }
}
