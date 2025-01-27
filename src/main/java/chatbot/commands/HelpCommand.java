package chatbot.commands;

import chatbot.IoHandler;

import java.util.Map;

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
     * A map containing the available commands
     */
    private final Map<String, Command> commands;

    /**
     * Constructs a HelpCommand with the specified IoHandler.
     *
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public HelpCommand(IoHandler ioHandler, Map<String, Command> commands) {
        super("help", "shows this message", "help");
        this.ioHandler = ioHandler;
        this.commands = commands;
    }

    /**
     * Executes the help command to display a list of commands available to the user.
     *
     * @param arguments This parameter is ignored as the command does not require arguments
     *                  to perform its function.
     */
    @Override
    public void execute(String arguments) {
        ioHandler.send("Here is what I can do:\n"
                + commands.values().stream()
                .map(Command::getCommandUsage)
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse(">> Opps, no commands available <<"));
    }
}
