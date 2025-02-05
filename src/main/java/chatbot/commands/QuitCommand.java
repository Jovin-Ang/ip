package chatbot.commands;

import chatbot.ui.IoHandler;
import javafx.application.Platform;

/**
 * Represents a command that terminates the chatbot gracefully.
 *
 * @author Jovin Ang
 */
public class QuitCommand extends Command {
    /**
     * Reference to an IoHandler instance which Handles input and output operations for
     * the command.
     */
    private final IoHandler ioHandler;

    /**
     * Constructs a QuitCommand instance that allows the chatbot to terminate gracefully.
     *
     * @param ioHandler The IoHandler instance used to handle input and output operations.
     */
    public QuitCommand(IoHandler ioHandler) {
        super("quit", "bye bye :(", "quit");
        this.ioHandler = ioHandler;
    }

    /**
     * Executes the QuitCommand to terminate the chatbot gracefully.
     *
     * @param arguments This parameter is ignored as the command does not require arguments
     *                  to perform its function.
     */
    @Override
    public void execute(String arguments) {
        ioHandler.send("Bye :) Hope to see you again soon!");
        Platform.exit(); // Exit gracefully
    }
}
