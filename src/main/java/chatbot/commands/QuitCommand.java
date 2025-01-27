package chatbot.commands;

import chatbot.ChatBot;

/**
 * Represents a command that terminates the chatbot gracefully.
 *
 * @author Jovin Ang
 */
public class QuitCommand extends Command {
    /**
     * A reference to a ChatBot instance
     */
    private final ChatBot bot;

    /**
     * Constructs a QuitCommand instance that allows the chatbot to terminate gracefully.
     *
     * @param bot The ChatBot instance that this command will interact with.
     */
    public QuitCommand(ChatBot bot) {
        super("quit", "bye bye :(", "quit");
        this.bot = bot;
    }

    /**
     * Executes the QuitCommand to terminate the chatbot gracefully.
     *
     * @param arguments This parameter is ignored as the command does not require arguments
     *                  to perform its function.
     */
    @Override
    public void execute(String arguments) {
        bot.send("Bye :) Hope to see you again soon!");
        bot.stop();  // Call chatbot shutdown method
    }
}
