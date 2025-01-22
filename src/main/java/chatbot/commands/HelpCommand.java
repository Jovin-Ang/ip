package chatbot.commands;

import chatbot.IoHandler;

/**
 * Represents a command that provides a list of available commands
 * and their descriptions to the user.
 *
 * @author Jovin Ang
 */
public class HelpCommand extends Command {
    /**
     * Reference to an IoHandler instance which Handles input and output operations for
     * the command.
     */
    private final IoHandler ioHandler;

    /**
     * Constructs a HelpCommand with the specified IoHandler.
     *
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public HelpCommand(IoHandler ioHandler) {
        this.ioHandler = ioHandler;
    }

    /**
     * Executes the help command to display a list of commands available to the user.
     *
     * @param arguments This parameter is ignored as the command does not require arguments
     *                  to perform its function.
     */
    @Override
    public void execute(String arguments) {
        ioHandler.send(
                """
                Here is what I can do:
                `help` shows this message
                `todo <task>` adds a task to the tasklist
                `deadline <task> /<by when>` adds a deadline task to the tasklist
                `event <task> /<start /<end>` adds an event task to the tasklist
                `list` shows the tasklist
                `mark <task number>` marks task number as done
                `unmark <task number>` unmarks task number as done
                `quit` bye bye :(
                """);
    }
}
