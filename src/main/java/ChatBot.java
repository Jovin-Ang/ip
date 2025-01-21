import java.util.Scanner;

/**
 * This class implements a chatbot.
 *
 * @author Jovin Ang
 */
public class ChatBot {
    /**
     * Represents the name of the chatbot.
     * This variable stores the name associated with the chatbot instance.
     */
    private final String name;
    /**
     * Represents a Scanner object for reading user input.
     * This variable is used to handle input during the chatbot's interactions.
     */
    private Scanner scanner;

    /**
     * A constant string used to visually separate sections of text output.
     * The separator is displayed both before and after chatbot messages
     * to improve readability during interactions.
     */
    private static final String SEPARATOR = "____________________________________________________________";

    /**
     * Creates a new ChatBot instance.
     *
     * @param name The name of the chatbot.
     * @param scanner The Scanner object used to handle user input.
     */
    public ChatBot(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    /**
     * Initiates the chatbot's interaction with the user.
     */
    public void run() {
        this.send("Hello! I'm " + this.name + "\n What can I do for you?");
        this.send("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted message to the user.
     *
     * @param message The message to be printed.
     */
    private void send(String message) {
        System.out.println(SEPARATOR + "\n " + message + "\n" + SEPARATOR);
    }
}
